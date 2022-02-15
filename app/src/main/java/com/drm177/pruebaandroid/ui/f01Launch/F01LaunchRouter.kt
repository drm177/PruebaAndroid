package com.drm177.pruebaandroid.ui.f01Launch

import android.content.Context
import android.content.Intent
import com.drm177.pruebaandroid.ui.base.BaseActivityRouter
import com.drm177.pruebaandroid.ui.f02Main.F02MainRouter
import javax.inject.Inject

class F01LaunchRouter @Inject constructor() : BaseActivityRouter {
    override fun intent(activity: Context): Intent = Intent(activity, F01LaunchView::class.java)

    fun f02MainView(context: Context){
        F02MainRouter().launch(context)
    }
}