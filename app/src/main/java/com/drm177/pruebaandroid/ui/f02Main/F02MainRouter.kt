package com.drm177.pruebaandroid.ui.f02Main

import android.content.Context
import android.content.Intent
import com.drm177.pruebaandroid.ui.base.BaseActivityRouter
import javax.inject.Inject

class F02MainRouter @Inject constructor() : BaseActivityRouter{
    override fun intent(activity: Context): Intent = Intent(activity, F02MainView::class.java)
}