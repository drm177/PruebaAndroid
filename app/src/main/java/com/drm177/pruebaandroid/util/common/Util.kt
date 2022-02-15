package com.drm177.pruebaandroid.util.common

import android.app.Activity
import android.os.Build
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import javax.inject.Inject

class Util @Inject constructor() {
    fun appOE(myActivityReference: Activity) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            @Suppress("DEPRECATION")
            myActivityReference.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            myActivityReference.window.decorView.windowInsetsController!!.hide(WindowInsets.Type.statusBars())
        }
    }
}