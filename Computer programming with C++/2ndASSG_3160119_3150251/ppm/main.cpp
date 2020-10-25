#include <iostream>
#include "ppm/ppm.h"
#include "FilterGamma.h"
#include "FilterLinear.h"

using namespace std;
using namespace math;
using namespace imaging;

bool checkPPM(std::string filename);
bool checkFormat(const std::string& filename_format, const std::string& true_format);

int main(int argc, char *argv[]) {
	string filename;	
	string input;	   		  
							
	bool applied_filter = false;
	bool no_image = false;

	vector<Filter *> applyFilters;
	vector<string> filter_names;
								
	int i = 1;
	int linear_counter = 0;
	int gamma_counter = 0;
	int filter_counter = 0;

	if (argc > 1) {
		while (i < argc) {
			filename = argv[argc - 1];	
			if (argc > i + 2 && strcmp(argv[i], "-f") == 0) {
				i++;
				if (argc > i + 2 && strcmp(argv[i], "linear") == 0) {
					if (argc < i + 6) {
						cerr << "ERROR! Invalid format!";
						break;
					}
					applied_filter = true;
					Vec3<float> *whole_A = nullptr;
					Vec3<float> *whole_C = nullptr;
					float AR, AG, AB;
					float CR, CG, CB;

					AR = atof(argv[++i]);
					AG = atof(argv[++i]);
					AB = atof(argv[++i]);
					CR = atof(argv[++i]);
					CG = atof(argv[++i]);
					CB = atof(argv[++i]);

					whole_A = new Vec3<float>(AR, AG, AB);
					whole_C = new Vec3<float>(CR, CG, CB);

					FilterLinear * FL = new FilterLinear(*whole_A, *whole_C);
					applyFilters.push_back(FL);
					delete whole_A, whole_C;

					i++;
				}
				else if (argc > i + 2 && strcmp(argv[i], "gamma") == 0) {
					if (argc < i + 2) {
						cerr << "ERROR! Invalid format!";
						break;
					}
					applied_filter = true;
					float gamma_value = atof(argv[++i]);
					FilterGamma * FG = new FilterGamma(gamma_value);
					applyFilters.push_back(FG);
					i++;
				}
				else {
					cerr << "ERROR! Invalid format!";
					break;
				}
			}
			else {
				if (applied_filter) {
					if (checkPPM(loadedFilename)) {
						savedFilename = "filtered_" + loadedFilename.substr(0, loadedFilename.size() - 4) + ".ppm";
						format = loadedFilename.substr(loadedFilename.find_last_of(".") + 1);
						Image * image = new Image();
						if (image->load(loadedFilename, format)) {
							int size = image->getWidth() * image->getHeight();
							cout << "The width of the image: " << image->getWidth() << endl;
							cout << "The height of the image: " << image->getHeight() << endl;
							for (int j = 0; j < applyFilters.size(); j++) {
								*image = *applyFilters[j] << *image;
							}
							cout << "Image loaded successfully!" << endl;
							image->save(savedFilename, format);
							cout << "Image saved successfully!" << endl;
						}
						delete image;
						break;
					}
					else {
						cerr << "ERROR! Invalid  image format!" << endl;
						break;
					}
				}
			}
		}
	}
	else {
		float * linear_par = new float[6]; 
		cout << "> filter ";
		bool foundPPM = false;
		while (!foundPPM) {
			int count = 0;
			cin >> input; 
			if (isdigit(input[0])) {
				no_image = true;
				break;
			}
			else if (input == f_apply) {
				cin >> input;
				if (input == "linear") {
					applied_filter = true;
					for (int l = 0; l < 6; l++) {
						cin >> input;
						linear_par[l] = convertStringToNumber<float>(input);
					}
					count += 8;
					linear.setA(Color(linear_par[0], linear_par[1], linear_par[2]));
					linear.setC(Color(linear_par[3], linear_par[4], linear_par[5]));
					linear_application.push_back(linear);
					linear_counter++;
					filter_names.push_back(filter_1);
					filter_counter++;
				}
				else if (input == "gamma") {
					applied_filter = true;
					cin >> input;
					count += 3;
					gamma_value = convertStringToNumber<float>(input);
					gamma.setGamma(gamma_value);
					gamma_application.push_back(gamma);
					gamma_counter++;
					filter_names.push_back(filter_2);
					filter_counter++;
				}
				else if (input == "blur") {
					applied_filter = true;
					cin >> input;
					count += 3;
					N = convertStringToNumber<int>(input);
					blur = FilterBlur(N);
					blur_application.push_back(blur);
					blur_counter++;
					filter_names.push_back(filter_3);
					filter_counter++;
				}
				else if (input == "laplace") {
					applied_filter = true;
					count += 2;
					laplace_counter++;
					filter_names.push_back(filter_4);
					filter_counter++;
				}
				else {
					cerr << "ERROR! The filter " << input << " doesn't exist!" << endl;
					break;
				}
			}
			else if (input.find("ppm")) {
				filename = input;
				if (applied_filter) {
					foundPPM = true;
					count += 1;
					if (image->load(filename, "ppm")) {
						cout << endl;
						image->save(input, "ppm");
					}
					else {
						break;
					}
				}
			}
		}
	}
	system("pause");
	return 0;
}

bool checkPPM(std::string filename) {
	if (checkFormat(filename.substr(filename.find_last_of(".") + 1), "ppm")) {
		return true;
	}
	return false;
}

bool checkFormat(const std::string& filename_format, const std::string& true_format) {
	unsigned int format_size = filename_format.size();
	if (true_format.size() != format_size)
		return false;
	for (unsigned int i = 0; i < format_size; ++i)
		if (tolower(filename_format[i]) != tolower(true_format[i]))
			return false;
	return true;
}