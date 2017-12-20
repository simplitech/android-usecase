package br.com.martinlabs.usecase.context

import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.databinding.PersistPrincipalBinding
import br.com.martinlabs.usecase.viewmodel.PersistPrincipalVM

class PersistPrincipalAct : BaseAct() {

    var binding: PersistPrincipalBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.persist_principal);
        binding?.let {
            it.vm = PersistPrincipalVM(it)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}