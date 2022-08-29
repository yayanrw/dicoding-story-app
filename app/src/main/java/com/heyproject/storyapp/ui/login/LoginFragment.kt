package com.heyproject.storyapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var user: User

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

        viewModel.message.observe(viewLifecycleOwner) {
            Snackbar.make(view, it!!, Snackbar.LENGTH_SHORT).show()
        }

        viewModel.requestState.observe(viewLifecycleOwner) {
            if (it == RequestState.SUCCESS) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        viewModel.loginResult.observe(viewLifecycleOwner) {
            val userPreference = UserPreference(requireContext())

            user.userId = it.userId
            user.name = it.name
            user.token = it.token

            userPreference.setUser(user)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToRegisterScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun signIn() {
        viewModel.signIn(
            binding!!.edLoginEmail.text.toString(),
            binding!!.edLoginPassword.text.toString()
        )
    }
}