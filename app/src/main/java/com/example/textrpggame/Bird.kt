package com.example.textrpggame

fun main() {
    var bird = Bird("새")
    var chicken = Chicken("닭")
    var sparrow = Sparrow("참새")
    var pigeon = Pigeon("비둘기")
    var duck = Duck("오리")

    bird.fly()
    chicken.fly()
    sparrow.fly()
    pigeon.fly()
    duck.swim()
}

open class Bird(name:String) {
    var name: String = ""

    init {
        // this는 현재 클래스의 상태변수를 의미합니다
        // var name: String = ""
        this.name = name
    }

    fun fly() {
        println("${name} 날아요~")
    }

}

class Chicken(name: String) : Bird(name) {

}

class Sparrow(name: String) : Bird(name) {

}

class Pigeon(name: String) : Bird(name) {

}

class Duck(name: String) : Bird(name), WaterBirdBehavior {
    override fun swim() {
        println("${name}가 수영해요~")
    }
}