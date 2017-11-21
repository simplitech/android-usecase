package br.com.martinlabs.usecase.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class ItemDoPrincipal : BaseObservable(), Serializable {

    @Bindable
    var principal: Principal? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.principal)
        }

    @Bindable
    var idItemDoPrincipalPk: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.idItemDoPrincipalPk)
        }

    @Bindable
    var titulo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.titulo)
        }
}
