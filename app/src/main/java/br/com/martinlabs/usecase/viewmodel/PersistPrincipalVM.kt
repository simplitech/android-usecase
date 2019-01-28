package br.com.martinlabs.usecase.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import br.com.ilhasoft.support.validation.Validator
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.model.GrupoDoPrincipal
import br.com.martinlabs.usecase.model.Principal
import br.com.martinlabs.usecase.model.Tag
import br.com.martinlabs.usecase.service.Api
import br.com.simpli.model.RespException
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import me.tatarka.bindingcollectionadapter2.ItemBinding
import ru.gildor.coroutines.retrofit.await
import br.com.martinlabs.usecase.databinding.PersistPrincipalBinding

/**
 * Created by gil on 15/11/17.
 */

class PersistPrincipalVM {

    val binding: PersistPrincipalBinding
    val principal = ObservableField<Principal>()
    val allGrupoDoPrincipal = ObservableArrayList<GrupoDoPrincipal>()
    val allTag = ObservableArrayList<Tag>()
    val validator: Validator
    val loading = ObservableBoolean(false)
    val itemGrupoDoPrincipalBinding: ItemBinding<GrupoDoPrincipal?> = ItemBinding.of(BR.item, R.layout.spinner)

    constructor(binding: PersistPrincipalBinding) : super() {
        this.binding = binding
        validator = Validator(binding)
        var extra = BaseAct.i.intent?.getSerializableExtra("principal") as Principal? ?: Principal()
        principal.set(extra)
        allGrupoDoPrincipal.add(null) // first value is empty

        load()
    }

    fun load() = async(UI) {
        loading.set(true)

        try {
            val resp = Api.resources.getPrincipal(principal.get()?.idPrincipalPk?.get()).await()
            principal.set(resp.principal.get())
            allGrupoDoPrincipal.addAll(resp.allGrupoDoPrincipal)
            allTag.addAll(resp.allTag)
        } catch (e: RespException) {
            BaseAct.i.errorToast(e)
        }

        loading.set(false)
    }

    fun save() = async(UI) {
        if (validator.validate()) {
            loading.set(true)

            try {
                Api.resources.persistPrincipal(principal.get()!!).await()
                loading.set(false)
                BaseAct.i.successToast(R.string.app_persistedSuccessfully)
                BaseAct.i.finish()
            } catch (e: RespException) {
                BaseAct.i.errorToast(e)
            }

            loading.set(false)
        }
    }

}