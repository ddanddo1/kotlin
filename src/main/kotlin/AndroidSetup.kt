import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.options.BaseOptions
import io.appium.java_client.touch.TapOptions
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test
import java.net.MalformedURLException
import java.net.URL
import org.testng.annotations.BeforeMethod
import java.time.Duration

class sampleTest {
    private lateinit var driver: AndroidDriver

    @BeforeMethod
    fun setUp() {
        val options = BaseOptions()
            .amend("platformName", "Android")
            .amend("appium:platformVersion", "14.0")
            .amend("appium:deviceName", "Pixel 8 Pro API 34")
            .amend("appium:automationName", "UiAutomator2")
            .amend("appium:udid", "emulator-5554")
            .amend("appium:app", "/Users/lookai.an/Downloads/ApiDemos-debug (1).apk")
            .amend("appium:ensureWebviewsHavePages", true)
            .amend("appium:nativeWebScreenshot", true)
            .amend("appium:newCommandTimeout", 3600)
            .amend("appium:connectHardwareKeyboard", true)

        driver = AndroidDriver(getUrl(), options)
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
    fun sampleTest() {
        // 명시적 대기 설정
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))

        // XPath를 사용하여 요소 찾기 및 클릭
        val appElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"App\"]")))
        appElement.click()

        // 클릭 후 잠시 대기 (선택사항)
        Thread.sleep(2000)

        val activityElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"Activity\"]")))
        activityElement.click()

        // 잠시 대기 (선택사항)
        Thread.sleep(1000)

        val customTitleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"Custom Title\"]")))
        customTitleElement.click()

        // 잠시 대기 (선택사항)
        Thread.sleep(1000)

        // "Left is best" EditText 찾기 및 "타이틀" 입력
        val leftEditText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@content-desc=\"Left is best\"]")))
        leftEditText.clear()
        leftEditText.sendKeys("타이틀")

        // "Change Left" 버튼 찾기 및 클릭
        val changeLeftButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Change Left\"]")))
        changeLeftButton.click()

        // "Right is always right" EditText 찾기 및 "서브타이틀" 입력
        val rightEditText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@content-desc=\"Right is always right\"]")))
        rightEditText.clear()
        rightEditText.sendKeys("서브타이틀")

        // "Change Right" 버튼 찾기 및 클릭
        val changeRightButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Change Right\"]")))
        changeRightButton.click()

        // 입력 확인을 위한 대기
        Thread.sleep(1000)

    }

    @AfterMethod
    fun tearDown() {
        driver.quit()
    }
}
