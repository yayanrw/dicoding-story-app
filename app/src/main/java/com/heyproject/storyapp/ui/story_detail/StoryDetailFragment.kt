package com.heyproject.storyapp.ui.story_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.heyproject.storyapp.databinding.FragmentStoryDetailBinding
import com.heyproject.storyapp.model.UserPreference
import com.heyproject.storyapp.model.dataStore

class StoryDetailFragment : Fragment() {
    private var _binding: FragmentStoryDetailBinding? = null
    val binding = _binding!!
    private lateinit var userPreference: UserPreference
    private val args: StoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeActionBar()
        userPreference = UserPreference(requireContext().dataStore)

        binding.apply {
            story = args.storyItem
            executePendingBindings()
        }
    }

    private fun removeActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _binding?.unbind()
    }
}