package br.com.martinlabs.usecase.response

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import br.com.martinlabs.usecase.model.Endereco
import java.io.Serializable

class EnderecoResp : BaseObservable(), Serializable {

    @Bindable
    var endereco: Endereco? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.endereco)
            }
        }
}
