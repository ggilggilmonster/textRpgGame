package com.example.textrpggame

class CashShop private constructor() {
    private val bowPrice = 150
    private val staffPrice = 120

    companion object {
        @Volatile private var instance: CashShop? = null

        fun getInstance(): CashShop {
            // 외부에서 요청왔을때 instance가 null인지 검증
            if(instance == null) {
                // synchronized로 외부 쓰레드의 접근을 막음
                // 쓰레드는 다음챕터에서 소개합니다!
                // 쓰레드간의 객체상태 혼돈을 막기위해 사용한다고 이해해주세요
                synchronized(this) {
                    instance = CashShop()
                }
            }
            return instance!!
        }
    }

    fun purchaseWeapon(character:Character){    // 파라미터로 아처나 위자드는 부모 클래스가 Character이므로 업 캐스팅해서 받아옴.
        if(character is Archer) {
            character?.run {    // 기존엔 character.~~~~ 해서 지저분했지만 세이프티 연산자와 run을 통해 더욱 직관화 됨.
                if(money >= bowPrice) {   // character가 this 안에 들어가서 character. 이나 this. 을 안 써도 바로 접근할 수 있게 됨.
                    println("[구매 후 금액]: [${money} - ${bowPrice}] = ${money-bowPrice}")
                    money -= bowPrice
                    weapons.add("슈퍼 활")
                } else {
                    println("돈이 부족합니다.")
                }
            }
        } else if(character is Wizard) {
            character?.run {
                if(money >= staffPrice) {
                    println("[구매 후 금액]: [${money} - ${staffPrice}] = ${money-staffPrice}")
                    money -= staffPrice
                    weapons.add("슈퍼 스태프")
                } else {
                    println("돈이 부족합니다.")
                }
            }
        }
    }

}