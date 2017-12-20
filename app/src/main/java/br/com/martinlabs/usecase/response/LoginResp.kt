package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable

class LoginResp : BaseObservable(), Serializable {

    @Bindable
    var token: String? = null
    set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.token)
            }
        }

    @Bindable
    var id: Long = 0
    set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.id)
            }
        }

}