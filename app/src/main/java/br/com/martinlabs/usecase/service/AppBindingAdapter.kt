package br.com.martinlabs.usecase.service

import android.databinding.BindingAdapter
import android.view.View
import android.widget.EditText

/**
 * Created by gil on 19/11/17.
 */

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
