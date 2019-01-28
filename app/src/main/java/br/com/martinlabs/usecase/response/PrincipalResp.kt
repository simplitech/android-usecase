package br.com.martinlabs.usecase.response

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import br.com.martinlabs.usecase.model.GrupoDoPrincipal
import br.com.martinlabs.usecase.model.Principal
import br.com.martinlabs.usecase.model.Tag
import java.io.Serializable

class PrincipalResp : Serializable {

    val principal = ObservableField<Principal>()
    val allGrupoDoPrincipal = ObservableArrayList<GrupoDoPrincipal>()
    val allTag = ObservableArrayList<Tag>()
}
