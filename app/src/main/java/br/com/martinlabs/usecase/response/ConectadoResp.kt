package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.Conectado
import java.io.Serializable

class ConectadoResp : BaseObservable(), Serializable {

    @Bindable
    var conectado: Conectado? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.conectado)
        }
}
