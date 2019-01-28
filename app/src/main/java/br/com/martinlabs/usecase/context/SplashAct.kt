package br.com.martinlabs.usecase.context


import android.os.Handler

/**
 * Created by ricardoprado on 12/03/18.
 */

class SplashAct : BaseAct() {

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            startActivityClearTask(LoginAct::class)
        }, 1000)
    }
}