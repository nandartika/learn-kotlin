package com.anushka.navdemo1

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.anushka.navdemo1.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.button.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editText.text)) {
                val bundle = bundleOf("user_input" to binding.editText.text.toString())
                it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment, bundle)
            } else {
                Toast.makeText(activity, "Please insert your name!", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }
}