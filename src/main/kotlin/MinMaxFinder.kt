class Solution {
    fun solution(s: String): String {
        val numbers = s.split(" ").map { it.toInt() }
        require(numbers.size >= 2) { "입력은 둘 이상의 정수를 포함해야 합니다." }

        val min = numbers.min()
        val max = numbers.max()

        return "($min) ($max)"
    }
}

fun main() {
    val sol = Solution()

    while (true) {
        println("공백으로 구분된 둘 이상의 정수를 입력하세요 (종료하려면 'exit' 입력):")
        val input = readLine()

        if (input == "exit") {
            println("프로그램을 종료합니다.")
            break
        }

        try {
            val result = sol.solution(input ?: "")
            println("결과: $result")
        } catch (e: IllegalArgumentException) {
            println("오류: ${e.message}")
        } catch (e: NumberFormatException) {
            println("오류: 올바른 정수를 입력해주세요.")
        }

        println() // 가독성을 위한 빈 줄
    }
}