package br.com.martinlabs.usecase.model

import android.databinding.ObservableField
import br.com.martinlabs.usecase.service.Hash

/**
 * Created by gil on 19/11/17.
 */
class LoginHolder() {

    val account = ObservableField<String>()
    val password = ObservableField<String>()

    constructor(account: String?, password: String?) : this() {
        this.account.set(account)
        this.password.set(password)
    }

    fun copyWithSha256OnPassword(): LoginHolder {
        return LoginHolder(account.get(), Hash.sha256(password.get() ?: ""))
    }

}