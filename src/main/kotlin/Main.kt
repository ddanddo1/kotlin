fun main() {
    while (true) {
        println("1부터 9까지의 숫자를 입력하세요 (종료하려면 exit를 입력하세요):")
        val input = readLine()
        if (input == "exit") {
            println("프로그램을 종료합니다.")
            break
        }

        val number = input?.toIntOrNull()
        if (number != null && number in 1..9) {
            println("-------------------------------------------------")
            println("${number}단을 실행합니다.")
            println("-------------------------------------------------")
            println("${number} 단 :")
            for (i in 1..9) {
                println("$number * $i = ${number * i}")
            }
            println("-------------------------------------------------")
        } else {
            println("잘못된 입력입니다. 1부터 9까지의 숫자를 입력해야 합니다.")
        }
    }
}
