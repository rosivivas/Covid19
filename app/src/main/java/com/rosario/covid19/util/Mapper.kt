package com.rosario.covid19.util

abstract class Mapper<in T,E> {
    abstract fun mapper(inputElement: T): E

}