package com.heyproject.storyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyproject.storyapp.databinding.ItemStoryBinding
import com.heyproject.storyapp.network.response.ListStoryItem

class StoryAdapter :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {
    private lateinit var stories: List<ListStoryItem>

    fun submitList(stories: List<ListStoryItem>?) {
        this.stories = stories ?: listOf()
    }

    class StoryViewHolder(var binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listStoryItem: ListStoryItem) {
            binding.tvName.text = listStoryItem.name
            binding.tvDescription.text = listStoryItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        holder.bind(story)
    }

    override fun getItemCount(): Int = stories.size
}