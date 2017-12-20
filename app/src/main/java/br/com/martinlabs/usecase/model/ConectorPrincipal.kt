package br.com.martinlabs.usecase.model

import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.viewtools.Watchable
import java.io.Serializable


class ConectorPrincipal : Watchable(), Serializable {

    @Bindable
    var conectado: Conectado? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.conectado)
            }
        }

    @Bindable
    var principal: Principal? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.principal)
            }
        }

    @Bindable
    var titulo: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.titulo)
            }
        }
}
