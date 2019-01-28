package br.com.martinlabs.usecase.response

import android.databinding.ObservableField
import android.databinding.ObservableLong

class LoginResp {
    val token = ObservableField<String>()
    val id = ObservableLong()
}
