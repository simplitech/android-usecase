package br.com.martinlabs.usecase.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable


class Endereco : BaseObservable(), Serializable {

    @Bindable
    var idEnderecoPk: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.idEnderecoPk)
        }

    @Bindable
    var cep: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.cep)
        }

    @Bindable
    var zipcode: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.zipcode)
        }

    @Bindable
    var rua: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.rua)
        }

    @Bindable
    var nro: Long? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.nro)
        }

    @Bindable
    var cidade: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.cidade)
        }

    @Bindable
    var uf: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.uf)
        }

    @Bindable
    var latitude: Double? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.latitude)
        }

    @Bindable
    var longitude: Double? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.longitude)
        }
}
