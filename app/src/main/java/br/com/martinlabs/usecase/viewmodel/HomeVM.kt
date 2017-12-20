package br.com.martinlabs.usecase.viewmodel

import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.context.ListPrincipalAct
import br.com.martinlabs.usecase.viewtools.Watchable

/**
 * Created by gil on 15/11/17.
 */

class HomeVM : Watchable() {

    fun openListPrincipal() {
        BaseAct.instance?.startActivity(ListPrincipalAct::class)
    }

}