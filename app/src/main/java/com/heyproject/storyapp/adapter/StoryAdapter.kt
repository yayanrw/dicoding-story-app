package com.heyproject.storyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heyproject.storyapp.databinding.ItemStoryBinding
import com.heyproject.storyapp.network.response.ListStoryItem

class StoryAdapter :
    ListAdapter<ListStoryItem, StoryAdapter.StoryViewHolder>(DiffCallback) {
    var onItemClick: ((ListStoryItem) -> Unit)? = null

    inner class StoryViewHolder(var binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listStoryItem: ListStoryItem?) {
            binding.apply {
                tvItemName.text = listStoryItem?.name
                story = listStoryItem
                executePendingBindings()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ListStoryItem>() {
        override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
            return oldItem.name == newItem.name && oldItem.description == newItem.description
        }

    }

}