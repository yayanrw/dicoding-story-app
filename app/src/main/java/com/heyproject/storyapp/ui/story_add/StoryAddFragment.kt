package com.heyproject.storyapp.ui.story_add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heyproject.storyapp.R

class StoryAddFragment : Fragment() {

    companion object {
        fun newInstance() = StoryAddFragment()
    }

    private lateinit var viewModel: StoryAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_story_add, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StoryAddViewModel::class.java)
        // TODO: Use the ViewModel
    }

}