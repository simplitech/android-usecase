package br.com.martinlabs.usecase.context

import android.os.Bundle
import br.com.martinlabs.usecase.R
import android.databinding.DataBindingUtil
import br.com.martinlabs.usecase.databinding.ListPrincipalBinding
import br.com.martinlabs.usecase.viewmodel.ListPrincipalVM


class ListPrincipalAct : BaseAct() {

    var binding: ListPrincipalBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.list_principal);
        binding?.vm = ListPrincipalVM()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
