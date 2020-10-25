#include "FilterGamma.h"
#include "FilterLinear.h"
#include "ppm/ppm.h"
#include <iostream>
using namespace std;
using namespace imaging;
using namespace math;

bool checkPPM(std::string filename); 
bool checkFormat(const std::string& FF, const std::string& TF);

int main(int argc,char * argv[]) {

	vector<Filter *> applyFilters;
	string LF;
	string format;
	string SF;
	int i = 1;
	while (i < argc) {
		if (argc>i+2&& strcmp(argv[i], "-f") == 0) {
			i++;
			if (argc>i+2&& strcmp(argv[i], "linear") == 0) {
				if ((argc < i + 6)) {	
					cerr << "Wrong Format";
					return 1;
				}
				Vec3<float> *whole_A = nullptr;
				Vec3<float> *whole_C = nullptr;
				float AR, AG, AB;
				float CR, CG, CB;
				AR = atof(argv[++i]);
				AG = atof(argv[++i]);
				AB = atof(argv[++i]);
				CR = atof(argv[++i]),
				CG = atof(argv[++i]),
				CB = atof(argv[++i]);
				
				whole_A = new Vec3<float>(AR, AG, AB);
				whole_C = new Vec3<float>(CR, CG, CB);
				FilterLinear * FL = new FilterLinear(*whole_A, *whole_C);
				applyFilters.push_back(FL);
				delete whole_A, whole_C;
				i++;
			}
			else if (argc>i+2&&strcmp(argv[i], "gamma") == 0) {
				if (argc < i + 2) {
					cerr << "ERROR! Invalid format!";
					return 1;
				}
				float gamma_value = atof(argv[++i]);
				FilterGamma * FG = new FilterGamma(gamma_value);
				applyFilters.push_back(FG);
				i++;
			}
			else {
				cerr << "ERROR! Invalid format!";
				return 1;
			}
		}else if (i == argc - 1) {
			LF = argv[i++];
		}
		else {
			cerr << "ERROR! Invalid format!";
			return 1;
		}
		cout << i;
	}
	if (checkPPM(LF)) {
		SF = "filtered_"+ LF.substr(0, LF.size() - 4) + ".ppm";
		format = LF.substr(LF.find_last_of(".") + 1);
		unsigned int height = 1200;
		unsigned int width = 750;
		Image * image = new Image();
		if (image->load(LF, format)) {
			int size = image->getWidth() * image->getHeight();
			cout << "The width of the image: " << image->getWidth() << endl;
			cout << "The height of the image: " << image->getHeight() << endl;
			for (int i = 0; i < applyFilters.size(); i++) {
				*image = *applyFilters[i] << *image;
			}
			cout << "Image loaded successfully!" << endl;
			image->save(SF, format);
			cout << "Image saved successfully!" << endl;

		}
		delete image;
	}
	else {
		cerr << "ERROR! Invalid image format!" << endl;
		return 1;
	}

	return 0;
	
}
bool checkPPM(std::string filename) {
	if (checkFormat(filename.substr(filename.find_last_of(".") + 1), "ppm")) {
		return true;
	}
	return false;
}
bool checkFormat(const std::string& FF, const std::string& TF)
{
	unsigned int sz = FF.size();
	if (TF.size() != sz)
		return false;
	for (unsigned int i = 0; i < sz; ++i)
		if (tolower(FF[i]) != tolower(TF[i]))
			return false;
	return true;
}