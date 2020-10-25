#include "FilterGamma.h"
#include "ppm/ppm.h"

using namespace imaging;
namespace math {
	
	FilterGamma::FilterGamma() {}
	
	FilterGamma::FilterGamma(float gamma) {
		this->gamma = gamma;
	}
	
	FilterGamma::FilterGamma(const FilterGamma &src) {
		gamma = src.gamma;
	}
	
	FilterGamma & FilterGamma:: operator = (const FilterGamma & right) {
		if (&right == this)
			return *this;
		gamma = right.gamma;
		return *this;
	}

	FilterGamma::~FilterGamma() {}

	Image FilterGamma::operator << (const Image & image) {
		int size = image.getHeight() * image.getWidth();
		for (int i = 0; i < size; i++) {
			if (gamma >= 0.5f && gamma <= 2.0f) {
				image.buffer[i].r = pow(image.buffer[i].r, gamma);
				image.buffer[i].g = pow(image.buffer[i].g, gamma);
				image.buffer[i].b = pow(image.buffer[i].b, gamma);
				image.buffer[i] = image.buffer[i].clampToLowerBound(0);
				image.buffer[i] = image.buffer[i].clampToUpperBound(1);
			}
		}
		return image;
	}
}