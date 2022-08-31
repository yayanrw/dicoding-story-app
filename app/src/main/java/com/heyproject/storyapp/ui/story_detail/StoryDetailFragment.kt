package com.heyproject.storyapp.ui.story_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.heyproject.storyapp.databinding.FragmentStoryDetailBinding
import com.heyproject.storyapp.network.response.ListStoryItem

class StoryDetailFragment : Fragment() {
    private var binding: FragmentStoryDetailBinding? = null
    private val args: StoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val storyItem = ListStoryItem(
            name = args.name,
            description = args.description,
            photoUrl = args.photoUrl
        )
        binding?.apply {
            story = storyItem
        }
        binding?.executePendingBindings()
    }
}