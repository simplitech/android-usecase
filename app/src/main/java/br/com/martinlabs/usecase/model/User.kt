package br.com.martinlabs.usecase.model

import br.com.martinlabs.usecase.viewtools.Watchable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class User : Watchable(), Serializable {

    @Bindable
    var idUserPk: Long = 0
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.idUserPk)
            }
        }

    @Bindable
    var email: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.email)
            }
        }

    @Bindable
    var senha: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.senha)
            }
        }
}
