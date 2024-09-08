import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.options.BaseOptions
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.net.MalformedURLException
import java.net.URL
import java.time.Duration

class SampleTest {
    private lateinit var driver: IOSDriver

    @BeforeMethod
    fun setUp() {
        val options = BaseOptions()
            .amend("platformName", "iOS")
            .amend("appium:platformVersion", "17.5")
            .amend("appium:automationName", "XCuiTest")
            .amend("appium:udid", "DFCA086C-A7FF-414C-B26D-B965B8692E31")
            .amend("appium:app", "/Users/lookai.an/Downloads/TestApp.app")
            .amend("appium:includeSafariInWebviews", true)
            .amend("appium:newCommandTimeout", 3600)
            .amend("appium:connectHardwareKeyboard", true)

        driver = IOSDriver(getUrl(), options)
    }

    private fun getUrl(): URL {
        return try {
            URL("http://127.0.0.1:4723")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            throw RuntimeException("Invalid URL")
        }
    }

    @Test
    fun SampleTest() {
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))

        // IntegerA 텍스트 필드 찾기 및 입력
        val integerAField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextField[@name='IntegerA']")))
        integerAField.click()
        integerAField.sendKeys("100")

        // IntegerB 텍스트 필드 찾기 및 입력
        val integerBField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTextField[@name='IntegerB']")))
        integerBField.click()
        integerBField.sendKeys("100")

        // ComputeSumButton 버튼 찾기 및 클릭
        val computeSumButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeButton[@name='ComputeSumButton']")))
        computeSumButton.click()

        // 결과 확인을 위한 대기
        Thread.sleep(2000)

    }

    @AfterMethod
    fun tearDown() {
        driver.quit()
    }
}
