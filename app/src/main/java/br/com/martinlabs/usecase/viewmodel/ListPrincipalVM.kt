package br.com.martinlabs.usecase.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.support.v7.widget.helper.ItemTouchHelper
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.callback.PrincipalCallback
import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.context.PersistPrincipalAct
import br.com.martinlabs.usecase.databinding.ListPrincipalBinding
import br.com.martinlabs.usecase.model.Principal
import br.com.martinlabs.usecase.service.Api
import br.com.martinlabs.usecase.viewtools.SwipeController
import br.com.simpli.model.RespException
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import me.tatarka.bindingcollectionadapter2.ItemBinding
import ru.gildor.coroutines.retrofit.await


/**
 * Created by gil on 15/11/17.
 */

class ListPrincipalVM {

    val binding: ListPrincipalBinding
    val listPrincipal = ObservableArrayList<Principal?>()
    val loading = ObservableBoolean(false)
    val itemPrincipalBinding: ItemBinding<Principal?> = ItemBinding.of(BR.item, R.layout.li_principal)

    constructor(binding: ListPrincipalBinding) {
        this.binding = binding
        load()
        itemPrincipalBinding.bindExtra(BR.itemClick, PrincipalCallback { principal -> principalClick(principal as Principal) })
    }

    fun load() = async(UI) {
        loading.set(true)

        try {
            var resp = Api.resources.listPrincipal(null, null, null, null, null).await()
            listPrincipal.addAll(resp.list)
        } catch (e: RespException){
            BaseAct.i.errorToast(e)
        }

        loading.set(false)
    }

    fun principalClick(principal: Principal) {
        BaseAct.i.startActivity(PersistPrincipalAct::class, "principal", principal)
    }

    fun newPrincipal() {
        BaseAct.i.startActivity(PersistPrincipalAct::class)
    }

}
