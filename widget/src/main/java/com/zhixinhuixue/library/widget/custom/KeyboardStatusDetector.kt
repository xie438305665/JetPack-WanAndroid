package com.zhixinhuixue.library.widget.custom

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener

class KeyboardStatusDetector(private val keyboardListener: KeyboardListener) :
    OnGlobalLayoutListener {
    private var keyboardVisible = false
    override fun onGlobalLayout() {
        val r = Rect()
        keyboardListener.keyboardView.getWindowVisibleDisplayFrame(r)
        val heightDiff =
            keyboardListener.keyboardView.rootView.height - (r.bottom - r.top)
        if (heightDiff > SOFT_KEY_BOARD_MIN_HEIGHT) {
            if (!keyboardVisible) {
                keyboardVisible = true
                keyboardListener.onVisibilityChanged(true)
            }
        } else {
            if (keyboardVisible) {
                keyboardVisible = false
                keyboardListener.onVisibilityChanged(false)
            }
        }
    }

    interface KeyboardListener {
        val keyboardView: View
        fun onVisibilityChanged(flag: Boolean)
    }

    companion object {
        private const val SOFT_KEY_BOARD_MIN_HEIGHT = 100
    }

}