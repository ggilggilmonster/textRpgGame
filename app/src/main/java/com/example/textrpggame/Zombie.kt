package com.example.textrpggame

class Zombie : Monster {    // 따로 인터페이스 만들 필요 없이 부모 클래스가 몬스터라는 거만 알려주면 됨.
    var name:String = ""
    var color:String = ""
    var height:Double = 0.0
    var hp:Int = 0
    var damage:Int = 0

    constructor(_name:String, _color:String, _height:Double, _hp:Int, _damage:Int) {

        name = _name
        color = _color
        height = _height
        hp = _hp
        damage = _damage

        println("${name}좀비 생성")
    }

    override fun attack() { // 그래서 attack을 오버라이딩함.
        println("물어뜯기 공격!")
    }

    fun virus() {
        println("바이러스 퍼뜨리기!")
    }
}