package br.com.martinlabs.usecase.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class Tag : BaseObservable(), Serializable {

    @Bindable
    var idTagPk: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.idTagPk)
        }

    @Bindable
    var titulo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.titulo)
        }

    @Bindable
    var tagPrincipal: MutableList<Principal>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.tagPrincipal)
        }
}
