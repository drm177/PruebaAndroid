package com.drm177.pruebaandroid.ui.f01Launch

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.drm177.pruebaandroid.util.common.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class F01LaunchViewModel @Inject constructor(
        private val router:F01LaunchRouter,
        private val util: Util
    ) : ViewModel() {

    //Propiedades
    companion object {
        private const val splashTimeOut = 3000
    }

    //Funciones
    fun inicio(activity: Activity, context: Context){
        util.appOE(activity)
        siguientePantalla(context)
    }

    private fun siguientePantalla(context: Context) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            f02MainView(context)
        }, splashTimeOut.toLong())
    }

    //Route
    private fun f02MainView(context: Context){
        router.f02MainView(context)
    }
}