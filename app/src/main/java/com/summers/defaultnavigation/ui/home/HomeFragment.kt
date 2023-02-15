package com.summers.defaultnavigation.ui.home

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.summers.defaultnavigation.R
import com.summers.defaultnavigation.base.ViewBindingFragment
import com.summers.defaultnavigation.databinding.FragmentHomeBinding


class HomeFragment : ViewBindingFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    companion object {
    }

    override fun onInitBinding(savedInstanceState: Bundle?) {
//        (activity as MainActivity).title = "Thingies"
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_secondFragment)
        }
    }

}