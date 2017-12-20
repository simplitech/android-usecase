package br.com.martinlabs.usecase.model

import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.viewtools.Watchable
import java.io.Serializable


class Conectado : Watchable(), Serializable {

    @Bindable
    var idConectadoPk: Long = 0
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.idConectadoPk)
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
