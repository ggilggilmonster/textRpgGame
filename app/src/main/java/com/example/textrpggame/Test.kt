package com.example.textrpggame

fun main() {
    var koreanScore = 88
    var englishScore = 92
    var mathScore = 99
    var average = (koreanScore + englishScore + mathScore) / 3

    when(average) {
        in 90..100 -> {
            println("당신의 등급은 A입니다")
        }
        in 80..89 -> {
            println("당신의 등급은 B입니다")
        }
        in 70..79 -> {
            println("당신의 등급은 C입니다")
        }
        else -> {
            println("당신의 등급은 F입니다")
        }
    }
}


