#include "FilterLinear.h"
#include "ppm/ppm.h"

using namespace imaging;
namespace math{
	FilterLinear::FilterLinear() {}
	
	FilterLinear::FilterLinear( Vec3<float> a, Vec3<float> c) {
		this->a = a;
		this->c = c;
	}
	
	FilterLinear::FilterLinear(const FilterLinear &src) {
		a = src.a;
		c = src.c;
	}
	
	FilterLinear & FilterLinear:: operator = (const  FilterLinear & right) {
		if (&right == this)
			return *this;
		a = right.a;
		c = right.c;
		return *this;
	}
	
	FilterLinear::~FilterLinear() {
		delete &a;
		delete &c;
	}
	
	Image FilterLinear::operator << (const Image & image) {
		int size = image.getHeight() * image.getWidth();
		for (int i = 0; i < size; i++) {
			image.buffer[i] = (a*image.buffer[i] + c);
			image.buffer[i] = image.buffer[i].clampToLowerBound(0);
			image.buffer[i] = image.buffer[i].clampToUpperBound(1);
		}
		return image;
	}
}


