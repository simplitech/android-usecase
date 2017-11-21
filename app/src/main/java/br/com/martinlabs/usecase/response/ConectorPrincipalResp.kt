package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.ConectorPrincipal
import br.com.martinlabs.usecase.model.Conectado
import br.com.martinlabs.usecase.model.Principal
import java.io.Serializable

class ConectorPrincipalResp : BaseObservable(), Serializable {

    @Bindable
    var conectorPrincipal: ConectorPrincipal? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.conectorPrincipal)
        }

    @Bindable
    var allConectado: MutableList<Conectado>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.allConectado)
        }

    @Bindable
    var allPrincipal: MutableList<Principal>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.allPrincipal)
        }
}
