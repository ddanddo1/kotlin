package mobile.tests

import mobile.base.TestBase
import mobile.views.AndroidAppView.assertLeftTitleText
import mobile.views.AndroidAppView.assertRightTitleText
import mobile.views.AndroidAppView.tapActivity
import mobile.views.AndroidAppView.tapApp
import mobile.views.AndroidAppView.tapCustomTitle
import mobile.views.AndroidAppView.tapLeftTitleTextButton
import mobile.views.AndroidAppView.tapRightTitleTextButton
import mobile.views.AndroidAppView.typeLeftTitleText
import mobile.views.AndroidAppView.typeRightTitleText
import org.testng.annotations.Test

class AndroidTest : TestBase {

    @Test
    fun sampleTest() {
        tapApp()
        tapActivity()
        tapCustomTitle()

        typeLeftTitleText("Hello, World!")
        tapLeftTitleTextButton()
        assertLeftTitleText("Hello, World!")

        typeRightTitleText("Hello, World!")
        tapRightTitleTextButton()
        assertRightTitleText("Hello, World!")
    }
}
