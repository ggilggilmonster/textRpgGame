package com.example.textrpggame

class Slime {
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

        println("${name}슬라임 생성")
    }

    fun attack() {
        println("점성 공격!")
    }

    fun jumpAttack() {
        println("점프해서 내려찍기!")
    }
}
