package br.com.martinlabs.usecase.model

import br.com.martinlabs.usecase.viewtools.Watchable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.service.Hash

/**
 * Created by gil on 19/11/17.
 */
class LoginHolder() : Watchable() {

    constructor(account: String?, password: String?) : this() {
        this.account = account
        this.password = password
    }

    @Bindable
    var account: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.account)
            }
        }

    @Bindable
    var password: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.password)
            }
        }

    fun copyWithSha256OnPassword(): LoginHolder {
        return LoginHolder(account, Hash.sha256(password ?: ""))
    }

}