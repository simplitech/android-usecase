package br.com.martinlabs.usecase.viewtools

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.view.View
import android.widget.EditText
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.databinding.InverseBindingListener
import android.widget.Spinner


/**
 * Created by gil on 19/11/17.
 */

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
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