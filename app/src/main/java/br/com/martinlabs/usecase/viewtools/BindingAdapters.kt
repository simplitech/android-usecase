package br.com.martinlabs.usecase.viewtools

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.databinding.BindingAdapter
import android.graphics.Paint
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.context.BaseAct
import com.bumptech.glide.Glide


/**
     * Created by gil on 19/11/17.
     */

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleFade")
fun setVisibleFade(view: View, visible: Boolean) {
    // Were we animating before? If so, what was the visibility?
    val endAnimVisibility = view.getTag(R.id.finalVisibility) as Boolean?
    val oldVisibility = endAnimVisibility ?: (view.visibility == View.VISIBLE)

    if (oldVisibility == visible) {
        // just let it finish any current animation.
        return
    }

    view.visibility = View.VISIBLE
    var startAlpha = if (oldVisibility) 1f else 0f
    if (endAnimVisibility != null) {
        startAlpha = view.alpha
    }
    val endAlpha = if (visible) 1f else 0f

    // Now create an animator
    val alpha = ObjectAnimator.ofFloat(view,
                                       View.ALPHA, startAlpha, endAlpha)
    alpha.setAutoCancel(true)

    alpha.addListener(object : AnimatorListenerAdapter() {
        private var isCanceled: Boolean = false

        override fun onAnimationStart(anim: Animator) {
            view.setTag(R.id.finalVisibility, visible)
        }

        override fun onAnimationCancel(anim: Animator) {
            isCanceled = true
        }

        override fun onAnimationEnd(anim: Animator) {
            view.setTag(R.id.finalVisibility, null)
            if (!isCanceled) {
                view.alpha = 1f
                view.visibility = if (visible) View.VISIBLE else View.GONE
            }
        }
    })
    alpha.start()
}

/**
     * if this method is called for the first time on the view with false it will not make the animation
     */
@BindingAdapter("visibleSlide")
fun setVisibleSlide(view: View, visible: Boolean) {

    val endAnimVisibility = view.getTag(R.id.finalVisibility) as Boolean?
    val oldVisibility = endAnimVisibility ?: false

    if (oldVisibility == visible) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return
    }

    var animation: Animation

    if (visible) {
        animation = AnimationUtils.loadAnimation(BaseAct.i,
                                                 R.anim.slide_up)
    } else {
        animation = AnimationUtils.loadAnimation(BaseAct.i,
                                                 R.anim.slide_down)
    }

    view.visibility = View.VISIBLE

    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}
        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            view.setTag(R.id.finalVisibility, visible)
            view.visibility = if (visible) View.VISIBLE else View.GONE
        }

    })

    animation.duration = 200

    view.startAnimation(animation)
}

@BindingAdapter("mask")
fun setMask(view: EditText, mask: String) {
    view.addTextChangedListener(MaskWatcher(mask))
}

@BindingAdapter("onBlur")
fun setOnBlur(view: EditText, listener: View.OnFocusChangeListener) {
    view.setOnFocusChangeListener { v, hasFocus ->
                                   if (!hasFocus) {
                                       listener.onFocusChange(v, hasFocus)
                                   }
                                  }
}

@BindingAdapter("srcUrl")
fun setSrcUrl(view: ImageView, url: String?) {
    if (url == null || url.isEmpty())
    return

    Glide.with(view.context).load(url)
    .into(view)
}

@BindingAdapter("widthInPx")
fun setLayoutWidth(view: View, width: Int) {
    val layoutParams = view.layoutParams
    layoutParams.width = width
    view.layoutParams = layoutParams
}

@BindingAdapter("heightInPx")
fun setLayoutHeight(view: View, height: Int) {
    val layoutParams = view.layoutParams
    layoutParams.height = height
    view.layoutParams = layoutParams
}

@BindingAdapter("strikeThrough")
fun setStrikeThrough(view: TextView, ignored: Boolean) {
    view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
