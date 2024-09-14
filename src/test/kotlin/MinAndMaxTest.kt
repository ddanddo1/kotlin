import org.testng.annotations.Test
import kotlin.test.assertEquals

class MinAndMaxTest {

    @Test
    fun solution_withPositiveNumbers_returnsMinAndMax() {
        val input = "1 2 3 4 5"
        val expected = "1 5"
        assertEquals(solution(input), expected)
    }

    @Test
    fun solution_withNegativeNumbers_returnsMinAndMax() {
        val input = "-1 -2 -3 -4 -5"
        val expected = "-5 -1"
        assertEquals(solution(input), expected)
    }

    @Test
    fun solution_withMixedNumbers_returnsMinAndMax() {
        val input = "3 -1 4 1 -5"
        val expected = "-5 4"
        assertEquals(solution(input), expected)
    }

    @Test
    fun solution_withSingleNumber_returnsSameNumberTwice() {
        val input = "42"
        val expected = "42 42"
        assertEquals(solution(input), expected)
    }

    @Test
    fun solution_withEmptyString_returnsEmptyString() {
        val input = ""
        try {
            solution(input)
            assert(false) { "Expected NumberFormatException" }
        } catch (e: NumberFormatException) {
            assert(true)
        }
    }

    @Test
    fun solution_withSameNumbers_returnsSameNumberTwice() {
        val input = "7 7 7 7"
        val expected = "7 7"
        assertEquals(solution(input), expected)
    }

    @Test
    fun solution_withLargeNumbers_returnsMinAndMax() {
        val input = "1000000 2000000 3000000 4000000 5000000"
        val expected = "1000000 5000000"
        assertEquals(solution(input), expected)
    }

    @Test
    fun solution_withZeroIncluded_returnsMinAndMax() {
        val input = "0 1 2 3 4 5"
        val expected = "0 5"
        assertEquals(solution(input), expected)
    }

    @Test
    fun solution_withDuplicateNumbers_returnsMinAndMax() {
        val input = "1 2 2 3 3 4 4 5 5"
        val expected = "1 5"
        assertEquals(solution(input), expected)
    }

    @Test
    fun solution_withNonNumericInput_throwsNumberFormatException() {
        val input = "1 2 three 4 5"
        try {
            solution(input)
            assert(false) { "Expected NumberFormatException" }
        } catch (e: NumberFormatException) {
            assert(true)
        }
    }

    @Test
    fun solution_withWhitespaceOnly_returnsEmptyString() {
        val input = "     "
        try {
            solution(input)
            assert(false) { "Expected NumberFormatException" }
        } catch (e: NumberFormatException) {
            assert(true)
        }
    }
}
