package br.com.martinlabs.usecase.context

import android.os.Bundle
import br.com.martinlabs.usecase.R
import android.databinding.DataBindingUtil
import br.com.martinlabs.usecase.databinding.ListPrincipalBinding
import br.com.martinlabs.usecase.viewmodel.ListPrincipalVM


class ListPrincipalAct : BaseAct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ListPrincipalBinding = DataBindingUtil.setContentView(this, R.layout.list_principal);
        binding.vm = ListPrincipalVM(binding)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
