package br.com.martinlabs.usecase.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.context.ListPrincipalAct
import br.com.martinlabs.usecase.model.LoginHolder
import br.com.martinlabs.usecase.service.Api
import com.simpli.model.RespException
import io.paperdb.Paper
import kotlinx.coroutines.experimental.launch

/**
 * Created by gil on 15/11/17.
 */

class LoginVM : BaseObservable() {

    var loginHolder: LoginHolder = LoginHolder()
    var loading: ObservableBoolean = ObservableBoolean(false)

    init {
        launch {
            var token: String? = Paper.book().read("token")
            if (token != null) {
                BaseAct.instance?.startActivityClearTask(ListPrincipalAct::class)
            }
        }
    }

    fun login() {
        launch {
            loading.set(true)

            try {
                Api.resources.login(loginHolder.copyWithSha256OnPassword()).execute().body()?.let {
                    loading.set(false)
                    Paper.book().write("token", it.token)
                    Paper.book().write("id", it.id)
                    BaseAct.instance?.startActivityClearTask(ListPrincipalAct::class)
                }
            } catch (e: RespException) {
                loading.set(false)
            }
        }
    }
}