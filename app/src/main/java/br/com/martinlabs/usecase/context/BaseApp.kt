package br.com.martinlabs.usecase.context

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import io.paperdb.Paper

/**
 * Created by gil on 20/11/17.
 */
class BaseApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Paper.init(this)
    }

}