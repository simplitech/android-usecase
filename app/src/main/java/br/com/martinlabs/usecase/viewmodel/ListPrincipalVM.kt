package br.com.martinlabs.usecase.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.util.Log
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.callback.WithIdAndTitleCallback
import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.context.PersistPrincipalAct
import br.com.martinlabs.usecase.model.Principal
import br.com.martinlabs.usecase.service.Api
import br.com.martinlabs.usecase.viewtools.Watchable
import com.simpli.model.RespException
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import me.tatarka.bindingcollectionadapter2.ItemBinding
import ru.gildor.coroutines.retrofit.await


/**
 * Created by gil on 15/11/17.
 */

class ListPrincipalVM : Watchable() {

    var listPrincipal: ObservableList<Principal?> = ObservableArrayList()
    var loading = ObservableBoolean(false)
    val itemPrincipalBinding: ItemBinding<Principal?> = ItemBinding.of(BR.item, R.layout.li_title)

    init {
        val load = load()
        itemPrincipalBinding.bindExtra(BR.itemClick, WithIdAndTitleCallback { principal -> principalClick(principal as Principal) })
    }

    fun load() = async(UI) {
        loading.set(true)

        try {
            var resp = Api.resources.listPrincipal(null, null, null, null, null).await()
            listPrincipal.addAll(resp.list)
        } catch (e: RespException){}

        loading.set(false)
    }

    fun principalClick(principal: Principal) {
        BaseAct.instance?.startActivity(PersistPrincipalAct::class, "principal", principal)
    }

    fun newPrincipal() {
        BaseAct.instance?.startActivity(PersistPrincipalAct::class)
    }


}
