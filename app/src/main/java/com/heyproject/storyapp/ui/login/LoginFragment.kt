package com.heyproject.storyapp.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.heyproject.storyapp.R
import com.heyproject.storyapp.databinding.FragmentLoginBinding
import com.heyproject.storyapp.model.User
import com.heyproject.storyapp.util.RequestState
import com.heyproject.storyapp.util.UserPreference

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var userPreference: UserPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            loginFragment = this@LoginFragment
        }

        removeActionBar(true)

        userPreference = UserPreference(requireContext())
        isLoggedIn()

        viewModel.requestState.observe(viewLifecycleOwner) {
            when (it) {
                RequestState.LOADING -> {
                    setLoading(true)
                }
                RequestState.ERROR -> {
                    setLoading(false)
                    Snackbar.make(view, getString(R.string.oops), Snackbar.LENGTH_SHORT).show()
                }
                RequestState.INVALID_CREDENTIALS -> {
                    setLoading(false)
                    Snackbar.make(
                        view,
                        getString(R.string.invalid_credentials),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                RequestState.NO_CONNECTION -> {
                    setLoading(false)
                    Snackbar.make(view, getString(R.string.no_connection), Snackbar.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    setLoading(false)
                }
            }
        }

        viewModel.loginResult.observe(viewLifecycleOwner) {
            val user = User()
            user.apply {
                userId = it.userId
                name = it.name
                token = it.token
            }
            userPreference.setUser(user)
            isLoggedIn()
        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.linearProgressIndicator?.visibility = View.VISIBLE
            binding?.btnSignIn?.isEnabled = false
        } else {
            binding?.linearProgressIndicator?.visibility = View.GONE
            binding?.btnSignIn?.isEnabled = true
        }
    }

    private fun removeActionBar(remove: Boolean) {
        if (remove) {
            (activity as AppCompatActivity).supportActionBar?.hide()
        } else {
            (activity as AppCompatActivity).supportActionBar?.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        removeActionBar(false)
    }

    fun goToRegisterScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun signIn() {
        if (formValidation()) {
            viewModel.signIn(
                binding!!.edLoginEmail.text.toString(),
                binding!!.edLoginPassword.text.toString()
            )
        }
    }

    private fun formValidation(): Boolean {
        var isValid = true
        if (binding?.edLoginEmail?.text.isNullOrEmpty()) {
            binding?.loginEmail?.error = getString(R.string.required)
            isValid = false
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(binding?.edLoginEmail?.text.toString()).matches()) {
                binding?.loginEmail?.error = getString(R.string.not_valid_email)
                isValid = false
            } else {
                binding?.loginEmail?.error = null
            }
        }

        if (binding?.edLoginPassword?.text.isNullOrEmpty()) {
            binding?.loginPassword?.error = getString(R.string.required)
            isValid = false
        } else {
            binding?.loginPassword?.error = null
        }
        return isValid
    }

    private fun isLoggedIn() {
        val user: User = userPreference.getUser()
        if (!user.token.isNullOrEmpty()) {
            findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
        }
    }
}