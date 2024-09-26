package mobile.views

import io.appium.java_client.AppiumBy
import mobile.base.BaseView
import mobile.driver.DriverManager


object IOSAppView : BaseView {

    override fun waitUntilOpenView() {
        TODO("Not yet implemented")
    }

    fun typeIntegerA(text: String) {
        val integerAElement = DriverManager.getDriver().findElement(AppiumBy.id("IntegerA"))
        type(integerAElement, text)
    }

    fun typeIntegerB(text: String) {
        val integerBElement = DriverManager.getDriver().findElement(AppiumBy.id("IntegerB"))
        type(integerBElement, text)
    }

    fun tapComputeSumButton() {
        val addButtonElement = DriverManager.getDriver().findElement(AppiumBy.id("ComputeSumButton"))
        tap(addButtonElement)
    }

    fun assertSumResult(text: String) {
        val sumResultElement = DriverManager.getDriver().findElement(AppiumBy.id("Answer"))
        assert(sumResultElement.text == text)
    }
}
