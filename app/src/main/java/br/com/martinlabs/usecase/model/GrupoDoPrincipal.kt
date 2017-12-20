package br.com.martinlabs.usecase.model

import br.com.martinlabs.usecase.viewtools.Watchable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class GrupoDoPrincipal : Watchable(), Serializable, WithIdAndTitle {

    override val id: Long?
        get() = idGrupoDoPrincipalPk

    override val title: String?
        get() = titulo

    @Bindable
    var idGrupoDoPrincipalPk: Long = 0
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.idGrupoDoPrincipalPk)
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
