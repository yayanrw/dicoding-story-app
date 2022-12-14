package com.heyproject.storyapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.heyproject.storyapp.R
import com.heyproject.storyapp.data.adapter.StoryAdapter
import com.heyproject.storyapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
//    private lateinit var userPreference: UserPreference
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showActionBar()
//        userPreference = UserPreference(requireContext().dataStore)

        storyAdapter = StoryAdapter()
//        storyAdapter.onItemClick = { selectedStory ->
//            val toStoryDetailFragment =
//                HomeFragmentDirections.actionHomeFragmentToStoryDetailFragment(selectedStory)
//            findNavController().navigate(toStoryDetailFragment)
//        }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
            homeFragment = this@HomeFragment
            screenError.homeFragment = this@HomeFragment
            rvStory.apply {
                setHasFixedSize(true)
                adapter = storyAdapter
            }
        }
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)


        fetchStories()

//        viewModel.getUser().observe(viewLifecycleOwner) {
//            if (!it.isLogin) {
//                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
//            }
//        }
//
//        viewModel.stories.observe(viewLifecycleOwner) {
//            storyAdapter.submitList(it)
//        }

//        viewModel.requestState.observe(viewLifecycleOwner) {
//            when (it) {
//                RequestState.LOADING -> {
//                    binding.circularProgressIndicator.visibility = View.VISIBLE
//                    binding.rvStory.visibility = View.GONE
//                    binding.screenError.root.visibility = View.GONE
//                }
//                RequestState.NO_DATA -> {
//                    binding.circularProgressIndicator.visibility = View.GONE
//                    binding.rvStory.visibility = View.GONE
//                    binding.screenError.root.visibility = View.VISIBLE
//                    binding.screenError.tvError.text = getString(R.string.no_data_available)
//                }
//                RequestState.ERROR -> {
//                    binding.circularProgressIndicator.visibility = View.GONE
//                    binding.rvStory.visibility = View.GONE
//                    binding.screenError.root.visibility = View.VISIBLE
//                    binding.screenError.tvError.text = getString(R.string.oops)
//                }
//                RequestState.NO_CONNECTION -> {
//                    binding.circularProgressIndicator.visibility = View.GONE
//                    binding.rvStory.visibility = View.GONE
//                    binding.screenError.root.visibility = View.VISIBLE
//                    binding.screenError.tvError.text = getString(R.string.no_connection)
//                }
//                else -> {
//                    binding.circularProgressIndicator.visibility = View.GONE
//                    binding.rvStory.visibility = View.VISIBLE
//                    binding.screenError.root.visibility = View.GONE
//                }
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
        fetchStories()
    }

    fun fetchStories() {
        viewModel.fetchStories()
    }

    fun goToStoryAddScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_storyAddActivity)
    }

    private fun showActionBar() {
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _binding?.unbind()
    }
}