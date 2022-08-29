package com.heyproject.storyapp.ui.story_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heyproject.storyapp.R

class StoryDetailFragment : Fragment() {

    companion object {
        fun newInstance() = StoryDetailFragment()
    }

    private lateinit var viewModel: StoryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_story_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StoryDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}