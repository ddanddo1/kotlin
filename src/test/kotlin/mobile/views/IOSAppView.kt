package mobile.views

import io.appium.java_client.AppiumBy
import mobile.base.BaseView
import mobile.driver.DriverManager.getDriver


object IOSAppView : BaseView {

    override fun waitUntilOpenView() {
        TODO("Not yet implemented")
    }

    fun typeIntegerA(text: String) {
        val integerAElement = getDriver().findElement(AppiumBy.id("IntegerA"))
        typeElement(integerAElement, text)
    }

    fun typeIntegerB(text: String) {
        val integerBElement = getDriver().findElement(AppiumBy.id("IntegerB"))
        typeElement(integerBElement, text)
    }

    fun tapComputeSumButton() {
        val addButtonElement = getDriver().findElement(AppiumBy.id("ComputeSumButton"))
        tapElement(addButtonElement)
    }

    fun assertSumResult(text: String) {
        val sumResultElement = getDriver().findElement(AppiumBy.id("Answer"))
        assert(sumResultElement.text == text)
    }
}
