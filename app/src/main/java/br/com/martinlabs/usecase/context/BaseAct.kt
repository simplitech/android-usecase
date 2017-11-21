package br.com.martinlabs.usecase.context

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import com.github.johnpersano.supertoasts.library.SuperToast
import kotlin.reflect.KClass


/**
 * Created by gil on 20/11/17.
 */
open class BaseAct : AppCompatActivity() {

    init {
        instance = this
    }

    companion object {
        var instance: BaseAct? = null
    }

    private fun toast(text: String, success: Boolean) {
        runOnUiThread {
            SuperToast.create(this, text, if (success) Style.DURATION_SHORT else Style.DURATION_MEDIUM, Style())
                    .setFrame(Style.FRAME_LOLLIPOP)
                    .setColor(PaletteUtils.getSolidColor(if (success) PaletteUtils.MATERIAL_GREEN else PaletteUtils.MATERIAL_RED))
                    .setAnimations(if (success) Style.ANIMATIONS_FLY else Style.ANIMATIONS_FADE)
                    .setGravity(Gravity.TOP)
                    .show()
        }
    }

    fun errorToast(text: String) {
        toast(text, false)
    }

    fun successToast(text: String) {
        toast(text, true)
    }

    fun startActivity(destination: KClass<*>) {
        val i = Intent(this, destination.java)
        startActivity(i)
    }

    fun startActivityClearTask(destination: KClass<*>) {

        val i = Intent(this, destination.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

}