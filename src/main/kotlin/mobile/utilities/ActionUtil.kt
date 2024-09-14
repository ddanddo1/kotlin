package mobile.utilities

import org.openqa.selenium.WebElement

interface ActionUtil : WaitUtil {

    fun tapElement(element: WebElement) {
        element.click()
        println("Tapping element: $element")
    }

    fun typeElement(element: WebElement, text: String) {
        element.clear()
        element.sendKeys(text)
        println("Typing text: $text to element: $element")
    }
}
