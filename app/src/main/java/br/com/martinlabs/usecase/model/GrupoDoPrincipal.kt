package br.com.martinlabs.usecase.model

import android.databinding.ObservableField
import android.databinding.ObservableLong
import java.io.Serializable


class GrupoDoPrincipal : Serializable, WithIdAndTitle {

    val idGrupoDoPrincipalPk = ObservableLong()
    val titulo = ObservableField<String>()

    override val id: Long?
        get() = idGrupoDoPrincipalPk.get()

    override val title: String?
        get() = titulo.get()
}
