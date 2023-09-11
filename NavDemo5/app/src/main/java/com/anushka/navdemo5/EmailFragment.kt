package com.anushka.navdemo5


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
import com.anushka.navdemo5.databinding.FragmentEmailBinding

/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
        val nameInput = requireArguments().getString("name_input")

        binding.submitButton.setOnClickListener {
            if (!TextUtils.isEmpty(binding.emailEditText.text)) {
                val bundle = bundleOf(
                    "name_input" to nameInput,
                    "email_input" to binding.emailEditText.text.toString()
                )
                it.findNavController()
                    .navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)
            } else {
                Toast.makeText(activity, "Please input your email!", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }
}
