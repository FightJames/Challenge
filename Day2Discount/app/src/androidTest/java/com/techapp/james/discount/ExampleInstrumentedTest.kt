package com.techapp.james.discount

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.KeyEvent

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import android.support.test.espresso.action.Press
import android.support.test.espresso.action.GeneralLocation
import android.support.test.espresso.action.GeneralClickAction
import android.support.test.espresso.action.Tap
import android.view.InputDevice
import android.view.MotionEvent.BUTTON_PRIMARY


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.techapp.james.discount", appContext.packageName)
    }

    @get:Rule
    public var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testUI() {
        onView(withId(R.id.priceEditText))
                .perform(click())
                .perform(typeText("Abc"), pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.discountPirceText))
                .check(matches(ViewMatchers.withText("$0.00")))

        onView(withId(R.id.priceEditText))
                .perform(click())
                .perform(typeText("123"), pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.discountPirceText))
                .check(matches(ViewMatchers.withText("$0.00")))
        onView(withId(R.id.seekBar)).perform(GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER_RIGHT, Press.FINGER, InputDevice.SOURCE_MOUSE, BUTTON_PRIMARY))
        onView(withId(R.id.discountPirceText))
                .check(matches(ViewMatchers.withText("$123.00")))
    }
}
