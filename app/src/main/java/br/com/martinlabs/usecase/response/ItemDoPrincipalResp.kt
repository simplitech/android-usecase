package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.ItemDoPrincipal
import br.com.martinlabs.usecase.model.Principal
import java.io.Serializable

class ItemDoPrincipalResp : BaseObservable(), Serializable {

    @Bindable
    var itemDoPrincipal: ItemDoPrincipal? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.itemDoPrincipal)
        }

    @Bindable
    var allPrincipal: MutableList<Principal>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.allPrincipal)
        }
}
