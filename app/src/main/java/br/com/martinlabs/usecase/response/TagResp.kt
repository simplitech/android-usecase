package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.Tag
import br.com.martinlabs.usecase.model.Principal
import java.io.Serializable

class TagResp : BaseObservable(), Serializable {

    @Bindable
    var tag: Tag? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.tag)
            }
        }

    @Bindable
    var allPrincipal: MutableList<Principal>? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.allPrincipal)
            }
        }
}
