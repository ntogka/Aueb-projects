#include <iostream>
#include "ppm/ppm.h"
#include "Image.h"


using namespace std;
using namespace imaging;

int main(int argc, char *argv[]) {

	string filename;
	if (argc <= 1) {
		cout << argv[0] << endl;
		cout << "\nFile name of the image to load: ";
		cin >> filename;
	}
	else {
		filename = argv[1];
	}
	Image *image = new Image();
	bool loading = image->load(filename, "ppm");
	for (size_t w = 0; w < image->getWidth(); w++) {
		for (size_t h = 0; h < image->getHeight(); h++) {
			image->setPixel(w, h, Color(1, 1, 1) - image->getPixel(w, h));	//p'(x,y) = (1,1,1) - p(x,y)
		}
	}
	if (loading) {
		image->save(filename, "ppm");
	}
	system("pause");
	return 0;
}