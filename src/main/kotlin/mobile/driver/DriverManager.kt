package mobile.driver

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import mobile.driver.DriverOption.setAndroidOptions
import mobile.driver.DriverOption.setIOSOptions
import java.net.URL

object DriverManager {

    private val driverThreadLocal = ThreadLocal<AppiumDriver>()

    fun setDriver(platform: String) {
        val driver : AppiumDriver = when (platform.lowercase()) {
            "android" -> AndroidDriver(serverUrl, setAndroidOptions())
            "ios" -> IOSDriver(serverUrl, setIOSOptions())
            else -> throw IllegalArgumentException("Unsupported platform: $platform")
        }
        driverThreadLocal.set(driver)
    }

    fun getDriver(): AppiumDriver {
        return driverThreadLocal.get() ?: throw IllegalStateException("Driver has not been set")
    }

    fun quitDriver() {
        driverThreadLocal.get().quit()
        driverThreadLocal.remove()
    }

    private val serverUrl = URL("http://127.0.0.1:4723")
}
