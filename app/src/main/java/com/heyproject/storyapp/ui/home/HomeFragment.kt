package com.heyproject.storyapp.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.heyproject.storyapp.R
import com.heyproject.storyapp.util.UserPreference

class HomeFragment : Fragment() {
    private lateinit var userPreference: UserPreference
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        removeBackArrow()
        userPreference = UserPreference(requireContext())
    }

    private fun removeBackArrow() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}