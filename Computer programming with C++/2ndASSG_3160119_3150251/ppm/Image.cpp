#include "Array.h"
#include <string>
#include "ppm/ppm.h"

using namespace imaging;
namespace math {

	bool checkPPM(std::string filename); 
	bool checkFormat(const std::string& filename_format, const std::string& true_format);

	Image::~Image() {}

	Image & Image::operator = (const Image & right) {
		width = right.width;
		height = right.height;
		buffer = right.buffer;
		return *this;
	}

	bool Image::load(const std::string & filename, const std::string & format) {
		if (checkFormat(format, "ppm")) {
			unsigned int * W = &width;
			unsigned int * H = &height;
			const char * initial_filename = filename.c_str();
			float * loadedData =  ReadPPM(initial_filename, (int*)W, (int*)H);
			/*width = *w;
			height = *h;
			int size = width * height;
			for (int i = 0; i < size; i++) {
				Vec3<float> * newColor = new Vec3<float>(loadedData[3 * i], loadedData[3 * i + 1], loadedData[3 * i + 2]);
				buffer.push_back(*newColor);
			}*/
			width = *W;
			height = *H;
			buffer.insert(buffer.begin(), (Color*)loadedData, (Color*)loadedData + (width*height));
			delete loadedData;
			return true;
		}
		return false;
	}

	bool Image::save(const std::string & filename, const std::string & format) {
		if (buffer.empty()) {
			return false;
		}
		if (checkFormat(format, "ppm")) {
			int triplexSize = 3 * height * width;
			int colorSize = height * width;
			float * savedData = new float[triplexSize];
			for (unsigned int i = 0; i < colorSize; i++) {
				savedData[3 * i] = buffer[i].r;
				savedData[3 * i + 1] = buffer[i].g;
				savedData[3 * i + 2] = buffer[i].b;
			}
			return WritePPM(savedData, width, height, filename.c_str());

		}
		return false;
	}

	bool checkPPM(std::string filename) {
		if (checkFormat(filename.substr(filename.find_last_of(".") + 1), "ppm")) {
			return true;
		}
		return false;
	}
	
	bool checkFormat(const std::string& filename_format, const std::string& true_format){
		unsigned int format_size = filename_format.size();
		if (true_format.size() != format_size)
			return false;
		for (unsigned int i = 0; i < format_size; ++i)
			if (tolower(filename_format[i]) != tolower(true_format[i]))
				return false;
		return true;
	}
}