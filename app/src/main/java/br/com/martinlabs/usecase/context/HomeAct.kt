package br.com.martinlabs.usecase.context

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.databinding.HomeBinding
import br.com.martinlabs.usecase.viewmodel.HomeVM

class HomeAct : BaseAct(), NavigationView.OnNavigationItemSelectedListener {

    var binding: HomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.home);
        binding?.vm = HomeVM(binding)

        initDrawer()
    }

    fun initDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, binding?.drawerLayout, binding?.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

        binding?.drawer?.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.principal -> {
                binding?.vm?.openListPrincipal()
            }
            R.id.logout -> {

            }
        }

        binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }
}
