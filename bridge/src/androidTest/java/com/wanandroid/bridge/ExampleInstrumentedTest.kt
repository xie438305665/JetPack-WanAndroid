package com.wanandroid.bridge

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented selector_article_collect, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under selector_article_collect.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.wanandroid.bridge.selector_article_collect", appContext.packageName)
    }
}