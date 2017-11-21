package br.com.martinlabs.usecase.viewmodel

import br.com.martinlabs.usecase.model.Principal

/**
 * Created by gil on 20/11/17.
 */
interface PrincipalCallback {

    fun call(principal: Principal)

}