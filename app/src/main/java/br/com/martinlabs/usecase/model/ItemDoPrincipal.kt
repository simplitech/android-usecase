package br.com.martinlabs.usecase.model

import br.com.martinlabs.usecase.viewtools.Watchable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class ItemDoPrincipal : Watchable(), Serializable {

    @Bindable
    var principal: Principal? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.principal)
            }
        }

    @Bindable
    var idItemDoPrincipalPk: Long = 0
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.idItemDoPrincipalPk)
            }
        }

    @Bindable
    var titulo: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.titulo)
            }
        }
}
