package br.com.martinlabs.usecase.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class User : BaseObservable(), Serializable {

    @Bindable
    var idUserPk: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.idUserPk)
        }

    @Bindable
    var email: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @Bindable
    var senha: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.senha)
        }
}
