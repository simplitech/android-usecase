package br.com.martinlabs.usecase.viewmodel

import android.databinding.ObservableBoolean
import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.context.HomeAct
import br.com.martinlabs.usecase.databinding.LoginBinding
import br.com.martinlabs.usecase.model.LoginHolder
import br.com.martinlabs.usecase.service.Api
import br.com.simpli.model.RespException
import io.paperdb.Paper
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import ru.gildor.coroutines.retrofit.await

/**
 * Created by gil on 15/11/17.
 */

class LoginVM {

    var binding: LoginBinding? = null
    val loginHolder = LoginHolder()
    val loading = ObservableBoolean(false)

    constructor(binding: LoginBinding?) {
        this.binding = binding
        val token: String? = Paper.book().read("token")
        if (token != null) {
            BaseAct.i.startActivityClearTask(HomeAct::class)
        }
    }

    fun login() = async(UI) {
        loading.set(true)

        try {
            var resp = Api.resources.login(loginHolder.copyWithSha256OnPassword()).await()
            loading.set(false)
            Paper.book().write("token", resp.token.get())
            Paper.book().write("id", resp.id.get())
            BaseAct.i.startActivityClearTask(HomeAct::class)
        } catch (e: RespException) {
            BaseAct.i.errorToast(e)
            loading.set(false)
        }
    }
}