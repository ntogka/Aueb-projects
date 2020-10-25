#pragma once
#include "Array.h"
#include "Vec3.h"
#include "ppm/ppm.h"
#ifndef _FILTER
#define _FILTER

using namespace imaging;
namespace math {
	class Filter {
	public:
		virtual Image operator << (const Image & image) = 0;
		virtual ~Filter() {}
	};
}
#endif