package mobile.base

import mobile.driver.DriverManager.createDriver
import mobile.driver.DriverManager.quitDriver
import mobile.enums.Platform
import mobile.utilities.ActionUtil
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass

interface TestBase : ActionUtil {

    companion object {
        val platform = when (System.getProperty("platform")) {
            "android" -> Platform.ANDROID
            "ios" -> Platform.IOS
            else -> throw RuntimeException("플랫폼 정보를 android 또는 ios로 설정해주세요.")
        }
    }

    @BeforeClass
    fun setUp() {
        createDriver(platform) // or DriverManager.Platform.ANDROID
        println("Setting up the test")
    }

    @AfterClass
    fun tearDown() {
        quitDriver()
        println("Tearing down the test")
    }
}
