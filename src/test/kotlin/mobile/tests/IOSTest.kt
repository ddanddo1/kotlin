package mobile.tests

import mobile.base.TestBase
import mobile.views.IOSAppView.assertSumResult
import mobile.views.IOSAppView.tapComputeSumButton
import mobile.views.IOSAppView.typeIntegerA
import mobile.views.IOSAppView.typeIntegerB
import org.testng.annotations.Test

class IOSTest : TestBase() {

    @Test
    fun sampleTest() {
        typeIntegerA("123")
        typeIntegerB("456")
        tapComputeSumButton()
        assertSumResult("579")
    }
}
