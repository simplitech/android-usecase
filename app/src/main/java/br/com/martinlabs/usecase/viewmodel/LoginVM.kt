package br.com.martinlabs.usecase.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.util.Log
import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.context.HomeAct
import br.com.martinlabs.usecase.context.ListPrincipalAct
import br.com.martinlabs.usecase.model.LoginHolder
import br.com.martinlabs.usecase.service.Api
import br.com.martinlabs.usecase.viewtools.Watchable
import com.simpli.model.RespException
import io.paperdb.Paper
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import ru.gildor.coroutines.retrofit.await

/**
 * Created by gil on 15/11/17.
 */

class LoginVM : Watchable() {

    var loginHolder = LoginHolder()
    var loading = ObservableBoolean(false)

    init {
        launch {
            var token: String? = Paper.book().read("token")
            if (token != null) {
                BaseAct.instance?.startActivityClearTask(HomeAct::class)
            }
        }
    }

    fun login() = async(UI) {
        loading.set(true)

        try {
            var resp = Api.resources.login(loginHolder.copyWithSha256OnPassword()).await()
            loading.set(false)
            Paper.book().write("token", resp.token)
            Paper.book().write("id", resp.id)
            BaseAct.instance?.startActivityClearTask(HomeAct::class)
        } catch (e: RespException) {
            loading.set(false)
        }
    }
}