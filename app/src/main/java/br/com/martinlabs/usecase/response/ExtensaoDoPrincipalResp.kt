package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.ExtensaoDoPrincipal
import br.com.martinlabs.usecase.model.Principal
import java.io.Serializable

class ExtensaoDoPrincipalResp : BaseObservable(), Serializable {

    @Bindable
    var extensaoDoPrincipal: ExtensaoDoPrincipal? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.extensaoDoPrincipal)
        }

    @Bindable
    var allPrincipal: MutableList<Principal>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.allPrincipal)
        }
}
