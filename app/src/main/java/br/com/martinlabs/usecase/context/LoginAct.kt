package br.com.martinlabs.usecase.context

import android.databinding.DataBindingUtil
import android.os.Bundle
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.databinding.LoginBinding
import br.com.martinlabs.usecase.viewmodel.LoginVM

/**
 * Created by gil on 20/11/17.
 */
class LoginAct : BaseAct() {

    var binding: LoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.login);
        binding?.vm = LoginVM()
    }
}