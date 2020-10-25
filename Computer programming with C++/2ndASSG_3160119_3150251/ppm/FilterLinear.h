#pragma once
#ifndef _FILTERLINE
#define _FILTERLINE

#include "Filter.h"
#include "ppm/ppm.h"

using namespace imaging;
namespace math {
	class FilterLinear : virtual public Filter {
		Vec3<float>  a,c;
	
	public:
		FilterLinear();
		FilterLinear(Vec3<float> a, Vec3<float> c);
		FilterLinear(const FilterLinear &src);
		FilterLinear & operator = (const FilterLinear &right);
		~FilterLinear();
		Image operator << (const Image & image);
	};
}
#endif