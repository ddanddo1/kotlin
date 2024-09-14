package mobile.driver

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import mobile.driver.DriverOption.createAndroidOptions
import mobile.driver.DriverOption.createIOSOptions
import mobile.enums.Platform
import java.net.MalformedURLException
import java.net.URL

object DriverManager {

    private lateinit var driver: AppiumDriver

    // 플랫폼에 따라 드라이버 생성
    fun createDriver(platform: Platform): AppiumDriver {
        driver = when (platform) {
            Platform.ANDROID -> AndroidDriver(getUrl(), createAndroidOptions())
            Platform.IOS ->  IOSDriver(getUrl(), createIOSOptions())
        }

        return driver
    }

    // 드라이버 가져오기
    fun getDriver(): AppiumDriver {
        return driver
    }

    // 드라이버 종료
    fun quitDriver() {
        driver.quit()
    }

    private fun getUrl(): URL {
        return try {
            URL("http://127.0.0.1:4723")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            throw RuntimeException("Invalid URL")
        }
    }
}
