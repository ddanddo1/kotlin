package mobile.views

import io.appium.java_client.AppiumBy
import mobile.base.BaseView
import mobile.driver.DriverManager

object AndroidAppView : BaseView {

    override fun waitUntilOpenView() {
        TODO("Not yet implemented")
    }

    fun tapApp() {
        val appElement = DriverManager.getDriver().findElement(AppiumBy.xpath("//*[@text='App']"))
        tap(appElement)
    }

    fun tapActivity() {
        val activityElement = DriverManager.getDriver().findElement(AppiumBy.xpath("//*[@text='Activity']"))
        tap(activityElement)
    }

    fun tapCustomTitle() {
        val customTitleElement = DriverManager.getDriver().findElement(AppiumBy.xpath("//*[@text='Custom Title']"))
        tap(customTitleElement)
    }

    fun typeLeftTitleText(text: String) {
        val leftTextElement = DriverManager.getDriver().findElement(AppiumBy.id("left_text_edit"))
        type(leftTextElement, text)
    }

    fun tapLeftTitleTextButton() {
        val leftTextButtonElement = DriverManager.getDriver().findElement(AppiumBy.id("left_text_button"))
        tap(leftTextButtonElement)
    }

    fun typeRightTitleText(text: String) {
        val rightTextElement = DriverManager.getDriver().findElement(AppiumBy.id("right_text_edit"))
        type(rightTextElement, text)
    }

    fun tapRightTitleTextButton() {
        val rightTextButtonElement = DriverManager.getDriver().findElement(AppiumBy.id("right_text_button"))
        tap(rightTextButtonElement)
    }

    fun assertLeftTitleText(text: String) {
        val leftTextElement = DriverManager.getDriver().findElement(AppiumBy.id("left_text"))
        assert(leftTextElement.text == text)
    }

    fun assertRightTitleText(text: String) {
        val rightTextElement = DriverManager.getDriver().findElement(AppiumBy.id("right_text"))
        assert(rightTextElement.text == text)
    }
}
