#include <iostream>
#include <fstream>
#include <string>
#include "ppm.h"

using namespace std;
namespace imaging {
	float * ReadPPM(const char * filename, int * w, int * h) {
		fstream ppmFile(filename, ios::in | ios::binary);
		string type;
		int max;
		if (!ppmFile.is_open()) {
			cout << "ERROR! Could not open the PPM file!" << endl;
			ppmFile.close();
			return nullptr;
		}
		else {
			ppmFile >> type;
			ppmFile >> *w;
			ppmFile >> *h;
			ppmFile >> max;
			ppmFile.ignore(256, '\n');	
			if (type.compare("P6") != 0) {
				cout << "Error loading " << endl;
				ppmFile.close();
				return nullptr;
			}
			else if (*w <= 0 || *h <= 0) {
				cout << "Error!" << endl;
				ppmFile.close();
				return nullptr;
			}
			else if (max != 255) {
				cout << "ERROR! Invalid maximum value! It should have been in the range [0,255]!" << endl;
				ppmFile.close();
				return nullptr;
			}
			else {
				cout << "\nImage dimensions are: " << *w << " X " << *h << "\n" << endl;
				int bufferSize = 3 * (*w)*(*h);
				unsigned char *readData = new unsigned char[bufferSize];
				float * imageData = new float[bufferSize];
				ppmFile.read((char*)readData, sizeof(int)*bufferSize);
				for (size_t i = 0; i < bufferSize; i++) {
					imageData[i] = (int)readData[i] / (float)max;
				}
				delete[] readData;
				return imageData;
			}
		}
	}

	bool WritePPM(const float * data, int w, int h, const char * filename) {
		fstream writeFile(filename, ios::out | ios::binary);
		writeFile.clear();

		if (!writeFile.is_open()) {
			return false;
		}
		writeFile << "P6" << endl << w << endl << h << endl << "255" << endl;
		int bufferSize = 3 * w * h;
		unsigned char *writtenData = new unsigned char[bufferSize];
		for (int i = 0; i < bufferSize; i++) {
			writtenData[i] = static_cast<unsigned char>(data[i] * 255.0f);
		}
		writeFile.write((char*)writtenData, bufferSize);
		writeFile.flush();	
		writeFile.close();
		return true;
	}
}