fun main() {
    // 콘솔에서 입력받기
    print("숫자 문자열을 입력하세요: ")
    val s = readLine() ?: ""
    println(solution(s))
}

fun solution(s: String): String {
    // 문자열을 공백 기준으로 분리하여 숫자 리스트로 변환
    val numbers = s.split(" ").map { it.toInt() }

    // 최소값과 최대값 찾기
    val minVal = getMinValue(numbers)
    val maxVal = getMaxValue(numbers)

    // "(최소값) (최대값)" 형태의 문자열로 반환
    return "$minVal $maxVal"
}

fun getMinValue(numbers: List<Int>): Int {
    var min = numbers[0]

    for (number in numbers) {
        if (number < min) {
            min = number
        }
    }

    return min
}

fun getMaxValue(numbers: List<Int>): Int {
    var max = numbers[0]

    for (number in numbers) {
        if (number > max) {
            max = number
        }
    }

    return max
}
