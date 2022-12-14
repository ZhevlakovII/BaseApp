package com.izhxx.baseapp.utilities

import android.content.Context
import androidx.startup.Initializer
import com.squareup.picasso3.Picasso

class PicassoInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        appContext = context
    }

    override fun dependencies() = emptyList<Class<Initializer<*>>>()

    companion object {
        private lateinit var appContext: Context
        private val instance: Picasso by lazy {
            Picasso
                .Builder(appContext)
                .build()
        }
        fun get() = instance
    }
}