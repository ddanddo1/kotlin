package mobile.utilities

import mobile.driver.DriverManager
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

interface WaitUtil {

    fun waitForElementEnabled(element: WebElement, timeout: Int): Boolean {
        val wait = WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout.toLong()))
        return wait.until { element.isEnabled }
    }
}
