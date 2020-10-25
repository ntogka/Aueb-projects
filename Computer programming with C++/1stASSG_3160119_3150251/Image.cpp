#include <iostream>
#include <string>
#include "Image.h"
#include "ppm/ppm.h"

namespace imaging {

	Color * Image::getRawDataPtr() {
		return buffer;
	}

	Color Image::getPixel(unsigned int x, unsigned int y) const {
		if ((x <= width)){
			if(y <= height) {
				return buffer[y*width + x];	
			}
		}
		else {
			return Color();	
		}
	}

	void Image::setPixel(unsigned int x, unsigned int y, Color & value) {
		if (x <= width && y <= height) {
			buffer[y*width + x] = value;
		}
	}

	void Image::setData(const Color * & data_ptr) {
		if ((width == 0) || (height == 0)) {
			return;
		}
		else {
			*buffer = *data_ptr; 
		}
	}

	Image::Image() {
		width = 0;
		height = 0;
		buffer = nullptr;
	}

	Image::Image(unsigned int width, unsigned int height) {
		this->width = width;
		this->height = height;
		buffer = new Color[width*height];
	}

	Image::Image(unsigned int width, unsigned int height, const Color * data_ptr) {
		this->width = width;
		this->height = height;
		buffer = new Color[width*height];
		setData(data_ptr);
	}

	Image::Image(const Image &src) {
		width = src.width;
		height = src.height;
		buffer = new Color[width*height];
		for (size_t w = 0; w < width; w++) {
			for (size_t h = 0; h < height; h++) {
				Color value = src.getPixel(w, h);
				setPixel(w, h, value);
			}
		}
	}

	Image::~Image() {
		std::cout << "Deleting the image buffer..." << std::endl;
		delete[]buffer;
	}

	Image & Image::operator = (const Image & right) {

		this->Image::Image(right);
		return *this;
	}

	bool Image::load(const std::string & filename, const std::string & format) {
		std::string fname = filename.substr(filename.find(format)); 
		bool done = false;
		if (format.size() != 3) {
			return false;
		}
		else {
			for (size_t i = 0; i < format.size(); ++i) {
				if (tolower(*(format+i)) == tolower((*fname+i))) {
					done = true;
				}
			}
			if (done) {
				this->Image::Image();	
				int w, h;
				const char* data = filename.c_str();
				float *lData = ReadPPM(data, &w, &h);
				if (lData != nullptr) {
					width = w;
					height = h;
					buffer = (Color*)lData;
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}

	bool Image::save(const std::string & filename, const std::string & format) {
		std::string fname = filename.substr(filename.find(format));
		bool done = false;
		if (format.size() != 3) {
			return false;
		}
		for (size_t i = 0; i < format.size(); ++i) {
			if (tolower(*(format+i)) == tolower(*(fname+i))) {
				done = true;
			}
		}
		if (done) {
			std::string finalname  = filename.substr(0, filename.find(format)-1) + "_neg.ppm"; 
			const char* final_name = finalname.c_str();
			const float* Data = (float*)buffer;
			return WritePPM(Data, width, height, final_name);
		}
		return false;
	}
}
