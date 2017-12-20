package br.com.martinlabs.usecase.model

import br.com.martinlabs.usecase.viewtools.Watchable
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class Tag : Watchable(), Serializable {

    @Bindable
    var idTagPk: Long = 0
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.idTagPk)
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

    @Bindable
    var tagPrincipal: ObservableArrayList<Principal>? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.tagPrincipal)
            }
        }
}
