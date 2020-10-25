#pragma once
#ifndef _ARRAY
#define _ARRAY

#include <vector>
#include "Vec3.h"
#include <string>

using namespace std;
typedef math::Vec3<float> Color;

namespace math {

	template <typename T> class Array {
	protected:
		unsigned int height, width;
		
		public:
			mutable std::vector<T> buffer;
			const unsigned int getWidth() const { return width; }
			const unsigned int getHeight() const { return height; }
			vector<T> & getRawDataPtr() ;
			T get(unsigned int x, unsigned int y) const;
			void set(unsigned int x, unsigned int y, T & value);
			void setData(const vector<T>  & data_ptr);
			Array();
			Array(const Array &src);
			Array(unsigned int width, unsigned int height);
			Array(unsigned int width, unsigned int height, const vector<T>  data_ptr);
			~Array();
			Array & operator = (const Array & right);
			T& operator()(int i, int j);

	};
	template <class T>
	std::vector<T> & Array<T>::getRawDataPtr() {
		return &buffer;
	}

	template <typename T>
	T Array<T>::get(unsigned int x, unsigned int y) const {
		if ((x <= getWidth()) && (y <= getHeight())) {
			T * element = buffer[width * (y - 1) + x];
			return *element;
		}
		else {
			T * temp = new T();
			return *temp;
		}
	}

	template <typename T>
	void Array<T>::set(unsigned int x, unsigned int y, T & value) {
		if ((x <= getWidth()) && (y <= getHeight())) {
			T * T_value = &get(x, y);
			*T_value = value;
		}
		else{
			return;
		}
	}

	template <typename T>
	void Array<T>::setData(const vector<T>  & data_ptr) {
		if ((width > 0) && (height > 0) && buffer.size() != 0) {
			for (unsigned int i = 0; i < buffer.size(); i++) {
				buffer[i] = data_ptr[i];
			}
		}
	}
	template <typename T>
	Array<T>::Array() :width(0), height(0) {
		buffer.resize(0);
	}
	
	template <typename T>
	Array<T>::Array(unsigned int width, unsigned int height){
		this->width = width;
		this->height = height;
		buffer.resize(width*height)
	}
	
	template <typename T>
	Array<T>::Array(unsigned int width, unsigned int height, const vector<T>  data_ptr) : width(width), height(height) {
		this->buffer = const_cast<vector<Vec3<float>>>(data_ptr);
	}

	template <typename T>
	Array<T>::Array(const Array &src) {
		width = src.width;
		height = src.height;
		buffer.resize(width*height);
		buffer = src.buffer;
	}

	template <typename T>
	Array<T>::~Array() {
		buffer.clear();
	}

	template <typename T>
	Array<T> & Array<T>::operator = (const Array<T> & right) {
		if (&right == this)
			return *this;
		width = right.width;
		height = right.height;
		buffer = right.buffer;
		return *(this);
	}

	template <typename T>
	T& Array<T>::operator()(int i, int j) {
		return get(i, j);
	}

	class Image : public Array<Color> {

	public:
		~Image();
		Image & operator = (const Image&right);
		bool load(const std::string & filename, const std::string & format);
		bool save(const std::string & filename, const std::string & format);
	};
}
#endif