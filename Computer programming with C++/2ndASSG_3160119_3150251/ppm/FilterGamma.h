#pragma once

#ifndef _FILTERGAMMA
#define _FILTERGAMMA

#include "Filter.h"
#include "ppm/ppm.h"

using namespace imaging;
namespace math {
	class FilterGamma : virtual public Filter {
		float gamma;
	public:
		FilterGamma();
		FilterGamma(float gamma);
		FilterGamma(const FilterGamma &src);
		FilterGamma & operator = (const FilterGamma &right);
		~FilterGamma();
		Image operator << (const Image & image);
	};
}
#endif // !_FILTERGAMMA