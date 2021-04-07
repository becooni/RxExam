package com.becooni.rxexam

const val HEXAGON = "HEXAGON"
const val OCTAGON = "OCTAGON"
const val RECTANGLE = "RECTANGLE"
const val TRIANGLE = "TRIANGLE"
const val DIAMOND = "DIAMOND"
const val PENTAGON = "PENTAGON"
const val BALL = "BALL"
const val STAR = "STAR"
const val NO_SHAPE = "NO_SHAPE"
const val FLIPPED = "(flipped)"

//Colors for shape
const val RED = "1"
const val YELLOW = "2"
const val GREEN = "3"
const val SKY = "4"
const val BLUE = "5"
const val PUPPLE = "6"
const val ORANGE = "7"

fun getShape(obj: String?): String {
    return when {
        obj.isNullOrBlank() -> NO_SHAPE
        obj.endsWith("-H") -> HEXAGON
        obj.endsWith("-O") -> OCTAGON
        obj.endsWith("-R") -> RECTANGLE
        obj.endsWith("-T") -> TRIANGLE
        obj.endsWith("<>") -> DIAMOND
        obj.endsWith("-P") -> PENTAGON
        obj.endsWith("-S") -> STAR
        else -> BALL
    }
}

fun getSuffix(shape: String): String {
    return when (shape) {
        HEXAGON -> "-H"
        OCTAGON -> "-O"
        RECTANGLE -> "-R"
        TRIANGLE -> "-T"
        DIAMOND -> "<>"
        PENTAGON -> "-P"
        STAR -> "-S"
        else -> ""
    }
}

fun getColor(shape: String): String {
    return if (shape.endsWith("<>")) {
        shape.replace("<>", "").trim()
    } else {
        val hyphen = shape.indexOf("-")
        if (hyphen > 0) shape.substring(0, hyphen) else shape
    }
}