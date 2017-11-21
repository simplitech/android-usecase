package br.com.martinlabs.usecase.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class GrupoDoPrincipal : BaseObservable(), Serializable {

    @Bindable
    var idGrupoDoPrincipalPk: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.idGrupoDoPrincipalPk)
        }

    @Bindable
    var titulo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.titulo)
        }
}
