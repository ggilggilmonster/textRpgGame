package com.example.textrpggame

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Random
import kotlin.concurrent.thread

class CashShop private constructor() {
    private val bowPrice = 150
    private val staffPrice = 120

    companion object {  // object로 돼있는 이유는 말이 두 마리 동시에 달리는데 1등한 말을 공유해야 함.
        @Volatile private var instance: CashShop? = null
        @Volatile private var lottoStatus: String? = null
        // 1등을 한 말의 이름을 저장하는 변수. 1등한 말이 없으면 달려야 하고 1등 한 말이 있으면 멈춰야 함. 1등 한 말의 이름을 공통적으로 알아야 함.
        @Volatile private var isFinish: Boolean? = null
        // 말 경주가 끝났는지 안 끝났는지(1등이 나왔는지 안 나왔는지)를 판별하기 위해.

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

    fun purchaseWeapon(character:Character){
        if(character is Archer) {
            character?.run {
                if(money >= bowPrice) {
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

    fun startLotto(character: Character, selectHorse: String) { // 외부에서 아처나 위자드 객체를 받아옴. 그리고 그 사람이 선택한 말 이름을 String으로 받아옴.
        var random = Random()
        val finalDst = 100
        isFinish = false
        thread(start = true) {
            var currentPosition = 0
            while(currentPosition < finalDst && isFinish == false) {
                currentPosition += (random.nextInt(5) + 1)
                // .nextInt 메소드를 사용하고 파라미터를 5로 넘김. 이렇게 하면 이 메소드 자체로는 0부터 4까지의 숫자를 리턴해주는 메소드. 근데 1을 더했으므로 1부터 5까지 리턴.
                // 이를 통해 말은 한번에 1미터에서 5미터까지 달릴 수 있다고 정의.

                println("1번말 현재 위치: ${currentPosition}m")
                runBlocking {
                    launch {
                        delay(1000)     // runBlocking, launch를 통해 delay로 1초마다 강제로 쉬게 함.
                    }
                }
            }
            if(lottoStatus == null || lottoStatus != "two") {
                lottoStatus = "one"
                isFinish = true
                println("1등: ${lottoStatus}말")

                if(lottoStatus.equals(selectHorse)) {
                    println("축하합니다! 당첨!")
                    println("상금으로 1만원 지급")

                    // 왜 이렇게밖에 작성했는지 이유를 생각하고
                    // 코드를 개선하기
                    if(character is Archer) {
                        character?.run {
                            money += 10000
                        }
                    } else if(character is Wizard) {
                        character?.run {
                            money += 10000
                        }
                    }
                }
            }

        }

        thread(start = true) {
            var currentPosition = 0
            while(currentPosition < finalDst && isFinish == false) {
                currentPosition += (random.nextInt(10) + 1)

                println("2번말 현재 위치: ${currentPosition}m")
                runBlocking {
                    launch {
                        delay(1000)
                    }
                }
            }
            if(lottoStatus == null || lottoStatus != "one") {
                lottoStatus = "two"
                isFinish = true
                println("1등: ${lottoStatus}말")
                if(lottoStatus.equals(selectHorse)) {
                    println("축하합니다! 당첨!")
                    println("상금으로 1만원 지급")

                    // 왜 이렇게밖에 작성했는지 이유를 생각하고
                    // 코드를 개선하기
                    if(character is Archer) {
                        character?.run {
                            money += 10000
                        }
                    } else if(character is Wizard) {
                        character?.run {
                            money += 10000
                        }
                    }
                }
            }

        }
    }
}