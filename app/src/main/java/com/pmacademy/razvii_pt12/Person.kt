package com.pmacademy.razvii_pt12

class Person(
    val name: String,
    val age: Int,
    var mother: Person? = null,
    var father: Person? = null,
) {
    override fun toString(): String {
        return "Name – $name"
    }
}

