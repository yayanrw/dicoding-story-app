package com.heyproject.storyapp.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.heyproject.storyapp.R
import com.heyproject.storyapp.databinding.FragmentRegisterBinding
import com.heyproject.storyapp.util.RequestState

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

        viewModel.requestState.observe(viewLifecycleOwner) {
            when (it) {
                RequestState.LOADING -> {
                    setLoading(true)
                }
                RequestState.ERROR -> {
                    setLoading(false)
                    Snackbar.make(view, getString(R.string.oops), Snackbar.LENGTH_SHORT).show()
                }
                RequestState.NO_CONNECTION -> {
                    setLoading(false)
                    Snackbar.make(view, getString(R.string.no_connection), Snackbar.LENGTH_SHORT)
                        .show()
                }
                RequestState.EMAIL_TAKEN -> {
                    setLoading(false)
                    Snackbar.make(view, getString(R.string.email_taken), Snackbar.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    setLoading(false)
                    Snackbar.make(view, getString(R.string.success_register), Snackbar.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.linearProgressIndicator?.visibility = View.VISIBLE
            binding?.btnRegister?.isEnabled = false
        } else {
            binding?.linearProgressIndicator?.visibility = View.GONE
            binding?.btnRegister?.isEnabled = true
        }
    }

    fun register() {
        if (formValidation()) {
            with(viewModel) {
                register(
                    binding!!.edRegisterName.text.toString(),
                    binding!!.edRegisterEmail.text.toString(),
                    binding!!.edRegisterPassword.text.toString()
                )
            }
        }
    }

    private fun formValidation(): Boolean {
        var isValid = true
        if (binding?.edRegisterName?.text.isNullOrEmpty()) {
            binding?.registerName?.error = getString(R.string.required)
            isValid = false
        } else {
            binding?.registerName?.error = null
        }

        if (binding?.edRegisterEmail?.text.isNullOrEmpty()) {
            binding?.registerEmail?.error = getString(R.string.required)
            isValid = false
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(binding?.edRegisterEmail?.text.toString())
                    .matches()
            ) {
                binding?.registerEmail?.error = getString(R.string.not_valid_email)
                isValid = false
            } else {
                binding?.registerEmail?.error = null
            }
        }

        if (binding?.edRegisterPassword?.text.isNullOrEmpty()) {
            binding?.registerPassword?.error = getString(R.string.required)
            isValid = false
        } else {
            binding?.registerPassword?.error = null
        }
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}