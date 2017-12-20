package br.com.martinlabs.usecase.model

import br.com.martinlabs.usecase.viewtools.Watchable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class Endereco : Watchable(), Serializable {

    @Bindable
    var idEnderecoPk: Long = 0
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.idEnderecoPk)
            }
        }

    @Bindable
    var cep: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.cep)
            }
        }

    @Bindable
    var zipcode: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.zipcode)
            }
        }

    @Bindable
    var rua: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.rua)
            }
        }

    @Bindable
    var nro: Long? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.nro)
            }
        }

    @Bindable
    var cidade: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.cidade)
            }
        }

    @Bindable
    var uf: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.uf)
            }
        }

    @Bindable
    var latitude: Double? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.latitude)
            }
        }

    @Bindable
    var longitude: Double? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.longitude)
            }
        }
}
