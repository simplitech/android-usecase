package br.com.martinlabs.usecase.model

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableLong
import java.io.Serializable


class Tag : Serializable {

    var tagPrincipal = ObservableArrayList<Principal>()
    var idTagPk = ObservableLong()
    var titulo = ObservableField<String>()
}
