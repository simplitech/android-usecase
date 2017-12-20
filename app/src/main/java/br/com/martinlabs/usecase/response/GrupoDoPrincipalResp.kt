package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.GrupoDoPrincipal
import java.io.Serializable

class GrupoDoPrincipalResp : BaseObservable(), Serializable {

    @Bindable
    var grupoDoPrincipal: GrupoDoPrincipal? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.grupoDoPrincipal)
            }
        }
}
