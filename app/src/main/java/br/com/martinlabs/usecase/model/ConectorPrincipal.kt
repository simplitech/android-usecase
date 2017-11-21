package br.com.martinlabs.usecase.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class ConectorPrincipal : BaseObservable(), Serializable {

    @Bindable
    var conectado: Conectado? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.conectado)
        }

    @Bindable
    var principal: Principal? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.principal)
        }

    @Bindable
    var titulo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.titulo)
        }
}
