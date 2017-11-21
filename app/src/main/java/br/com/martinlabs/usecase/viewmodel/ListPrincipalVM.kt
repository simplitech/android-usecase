package br.com.martinlabs.usecase.viewmodel

import android.databinding.*
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.model.Principal
import br.com.martinlabs.usecase.service.Api
import com.android.databinding.library.baseAdapters.BR
import com.simpli.model.RespException
import kotlinx.coroutines.experimental.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding


/**
 * Created by gil on 15/11/17.
 */

class ListPrincipalVM : BaseObservable() {

    var listPrincipal: ObservableList<Principal?> = ObservableArrayList()
    var loading: ObservableBoolean = ObservableBoolean(false)
//    val itemPrincipalBinding: ItemBinding<Principal?> = ItemBinding.of(BR.item, R.layout.li_principal)

    init {
        load()

//        itemPrincipalBinding.bindExtra(BR.itemClick, null)
    }

    fun load() {
        launch {
            loading.set(true)

            try {
                Api.resources.listPrincipal(null, null, null, null, null).execute().body()?.let {
                    listPrincipal.addAll(it.list)
                    loading.set(false)
                }
            } catch (e: RespException){
                loading.set(false)
            }
        }
    }

    fun principalClick(principal: Principal) {

    }


}
