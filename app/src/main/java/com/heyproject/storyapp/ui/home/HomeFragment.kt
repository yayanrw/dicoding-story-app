package com.heyproject.storyapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.heyproject.storyapp.R
import com.heyproject.storyapp.adapter.StoryAdapter
import com.heyproject.storyapp.databinding.FragmentHomeBinding
import com.heyproject.storyapp.network.response.ListStoryItem
import com.heyproject.storyapp.util.RequestState
import com.heyproject.storyapp.util.UserPreference

class HomeFragment : Fragment() {
    private lateinit var userPreference: UserPreference
    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreference = UserPreference(requireContext())

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            homeFragment = this@HomeFragment
            rvStory.adapter = StoryAdapter(listOf())
            rvStory.setHasFixedSize(true)
            screenError.homeFragment = this@HomeFragment
        }

        fetchStories()

        viewModel.stories.observe(viewLifecycleOwner) {
            storyAdapter = StoryAdapter(listOf())
            storyAdapter = StoryAdapter(it)
            binding?.rvStory?.adapter = storyAdapter

            storyAdapter.setOnItemClickCallBack(object : StoryAdapter.OnItemClickCallback {
                override fun onItemClicked(storyItem: ListStoryItem) {
                    val toStoryDetailFragment =
                        HomeFragmentDirections.actionHomeFragmentToStoryDetailFragment(
                            storyItem.name!!,
                            storyItem.description!!,
                            storyItem.photoUrl!!
                        )
                    findNavController().navigate(toStoryDetailFragment)
                }
            })
        }

        viewModel.requestState.observe(viewLifecycleOwner) {
            when (it) {
                RequestState.LOADING -> {
                    binding?.circularProgressIndicator?.visibility = View.VISIBLE
                    binding?.rvStory?.visibility = View.GONE
                    binding?.screenError?.root?.visibility = View.GONE
                    binding?.screenNodata?.root?.visibility = View.GONE
                }
                RequestState.NODATA -> {
                    binding?.circularProgressIndicator?.visibility = View.GONE
                    binding?.rvStory?.visibility = View.GONE
                    binding?.screenError?.root?.visibility = View.GONE
                    binding?.screenNodata?.root?.visibility = View.VISIBLE
                }
                RequestState.ERROR -> {
                    binding?.circularProgressIndicator?.visibility = View.GONE
                    binding?.rvStory?.visibility = View.GONE
                    binding?.screenError?.root?.visibility = View.VISIBLE
                    binding?.screenNodata?.root?.visibility = View.GONE
                }
                else -> {
                    binding?.circularProgressIndicator?.visibility = View.GONE
                    binding?.rvStory?.visibility = View.VISIBLE
                    binding?.screenError?.root?.visibility = View.GONE
                    binding?.screenNodata?.root?.visibility = View.GONE
                }
            }
        }

        viewModel.message.observe(viewLifecycleOwner) {
            binding?.screenError?.tvError?.text = it
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStoryList(userPreference.getUser().token!!)
    }

    fun fetchStories() {
        viewModel.getStoryList(userPreference.getUser().token!!)
    }

    fun goToStoryAddScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_storyAddActivity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        storyAdapter = StoryAdapter(listOf())
    }
}