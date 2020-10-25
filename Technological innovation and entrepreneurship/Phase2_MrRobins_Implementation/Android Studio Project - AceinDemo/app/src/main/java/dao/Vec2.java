package com.example.georgiosmoschovis.aceindemo.dao;

/**
 * This class illustrates a parameterized 2d vector.
 */
public class Vec2<T, Y>{
    /*
     * The actual T type data
     */
    protected T value1;

    /*
     * The actual Y type data
     */
    protected Y value2;

    /*
     * The T type container capacity
     */
    protected int Tsize;

    /*
     * The Y type container capacity
     */
    protected int Ysize;

    /*
     * Basic constructor setting automatically all fields equal to null (or 0.0).
     */
    public Vec2(){}

    /*
     * Overloaded constructor for two-dimensional vectors constructed in State.java; supporting several numerical
     * operators that can be used on Vec2<T, Y> and T, Y data.
     * value1 The actual T type data.
     * value2 The actual Y type data.
     */
    public Vec2(T value1, Y value2){
        this.value1 = value1;
        this.value2 = value2;
    }

    /*
     * Getter for value1: the actual T type data
     * value1 Value of the actual T type data
     */
    public T getTValue(){
        return value1;
    }

    /*
     * Getter for value2: the actual Y type data
     * value2 Value of the actual Y type data
     */
    public Y getYValue(){
        return value2;
    }

    /*
     * Getter for Tsize: the T type container capacity
     * Tsize The T type container capacity
     */
    public int getTSize(){
        return Tsize;
    }

    /*
     * Getter for Ysize: the Y type container capacity
     * Ysize The Y type container capacity
     */
    public int getYSize(){
        return Ysize;
    }

    /*
     * Setter for value1: the actual T type data
     * value1 Value of the actual T type data
     */
    public void setTValue(T value1){
        this.value1 = value1;
    }

    /*
     * Setter for value2: the actual Y type data
     * value2 Value of the actual Y type data
     */
    public void setYValue(Y value2){
        this.value2 = value2;
    }

    /*
     * Setter for Tsize: the T type container capacity
     * Tsize The T type container capacity
     */
    public void setTSize(int value1){
        this.Tsize = value1;
    }

    /*
     * Setter for Ysize: the Y type container capacity
     * Ysize The Y type container capacity
     */
    public void setYSize(int value2){
        this.Ysize = value2;
    }

    /*
     * Implementation of overridden method toString.
     */
    public String toString(){
        return this.value1 + ", " + this.value2;
    }
}