package com.heyproject.storyapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.heyproject.storyapp.R
import com.heyproject.storyapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var binding: FragmentRegisterBinding? = null
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding = registerBinding
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            registerFragment = this@RegisterFragment
        }
    }

    fun register() {
        if (formValidation()) {
            with(viewModel) {
                register(
                    binding!!.edRegisterName.text.toString(),
                    binding!!.edLoginEmail.text.toString(),
                    binding!!.edLoginPassword.text.toString()
                )
            }
        }
//        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun formValidation(): Boolean {
        var isValid = true
        if (binding?.edRegisterName?.text.isNullOrEmpty()) {
            binding?.registerName?.error = getString(R.string.required)
            isValid = false
        } else {
            binding?.registerName?.error = null
        }

        if (binding?.edLoginEmail?.text.isNullOrEmpty()) {
            binding?.registerEmail?.error = getString(R.string.required)
            isValid = false
        } else {
            binding?.registerEmail?.error = null
        }

        if (binding?.edLoginPassword?.text.isNullOrEmpty()) {
            binding?.registerPassword?.error = getString(R.string.required)
            isValid = false
        } else {
            binding?.registerPassword?.error = null
        }
        return isValid
    }
}