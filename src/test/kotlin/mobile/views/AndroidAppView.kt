package mobile.views

import io.appium.java_client.AppiumBy
import mobile.base.BaseView
import mobile.driver.DriverManager.getDriver

object AndroidAppView : BaseView {

    override fun waitUntilOpenView() {
        TODO("Not yet implemented")
    }

    fun tapApp() {
        val appElement = getDriver().findElement(AppiumBy.xpath("//*[@text='App']"))
        tapElement(appElement)
    }

    fun tapActivity() {
        val activityElement = getDriver().findElement(AppiumBy.xpath("//*[@text='Activity']"))
        tapElement(activityElement)
    }

    fun tapCustomTitle() {
        val customTitleElement = getDriver().findElement(AppiumBy.xpath("//*[@text='Custom Title']"))
        tapElement(customTitleElement)
    }

    fun typeLeftTitleText(text: String) {
        val leftTextElement = getDriver().findElement(AppiumBy.id("left_text_edit"))
        typeElement(leftTextElement, text)
    }

    fun tapLeftTitleTextButton() {
        val leftTextButtonElement = getDriver().findElement(AppiumBy.id("left_text_button"))
        tapElement(leftTextButtonElement)
    }

    fun typeRightTitleText(text: String) {
        val rightTextElement = getDriver().findElement(AppiumBy.id("right_text_edit"))
        typeElement(rightTextElement, text)
    }

    fun tapRightTitleTextButton() {
        val rightTextButtonElement = getDriver().findElement(AppiumBy.id("right_text_button"))
        tapElement(rightTextButtonElement)
    }

    fun assertLeftTitleText(text: String) {
        val leftTextElement = getDriver().findElement(AppiumBy.id("left_text"))
        assert(leftTextElement.text == text)
    }

    fun assertRightTitleText(text: String) {
        val rightTextElement = getDriver().findElement(AppiumBy.id("right_text"))
        assert(rightTextElement.text == text)
    }
}
