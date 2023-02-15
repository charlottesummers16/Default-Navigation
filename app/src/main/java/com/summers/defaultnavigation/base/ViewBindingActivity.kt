package com.summers.defaultnavigation.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.summers.defaultnavigation.R

private typealias ActivityViewBindingInflater<VB> = (inflater: LayoutInflater) -> VB

abstract class ViewBindingActivity<VB : ViewBinding>(
    private val viewBindingInflater: ActivityViewBindingInflater<VB>
) : AppCompatActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        onInitBinding(savedInstanceState)
    }

    abstract fun onInitBinding (savedInstanceState: Bundle?)

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    abstract fun showLoading()
    abstract fun hideLoading()

    fun showBackButton() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.baseline_home_24)
        }
    }

    fun hideBackButton() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setHomeButtonEnabled(false)
        }
    }
}