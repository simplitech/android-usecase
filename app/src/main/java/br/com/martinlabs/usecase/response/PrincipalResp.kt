package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.Principal
import br.com.martinlabs.usecase.model.GrupoDoPrincipal
import br.com.martinlabs.usecase.model.Tag
import java.io.Serializable

class PrincipalResp : BaseObservable(), Serializable {

    @Bindable
    var principal: Principal? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.principal)
        }

    @Bindable
    var allGrupoDoPrincipal: MutableList<GrupoDoPrincipal>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.allGrupoDoPrincipal)
        }

    @Bindable
    var allTag: MutableList<Tag>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.allTag)
        }
}
