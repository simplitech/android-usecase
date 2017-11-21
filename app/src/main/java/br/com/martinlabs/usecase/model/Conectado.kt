package br.com.martinlabs.usecase.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class Conectado : BaseObservable(), Serializable {

    @Bindable
    var idConectadoPk: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.idConectadoPk)
        }

    @Bindable
    var titulo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.titulo)
        }
}
