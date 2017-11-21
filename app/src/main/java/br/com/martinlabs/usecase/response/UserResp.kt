package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.User
import java.io.Serializable

class UserResp : BaseObservable(), Serializable {

    @Bindable
    var user: User? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.user)
        }
}
