package br.com.martinlabs.usecase.viewmodel

import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.context.ListPrincipalAct
import br.com.martinlabs.usecase.databinding.HomeBinding

/**
 * Created by gil on 15/11/17.
 */

class HomeVM {

    var binding: HomeBinding? = null

    constructor(binding: HomeBinding?) {
        this.binding = binding
    }

    fun openListPrincipal() {
        BaseAct.i.startActivity(ListPrincipalAct::class)
    }

}