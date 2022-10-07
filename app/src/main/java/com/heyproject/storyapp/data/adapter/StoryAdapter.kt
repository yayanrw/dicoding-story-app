package com.heyproject.storyapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.heyproject.storyapp.databinding.ItemStoryBinding
import com.heyproject.storyapp.domain.model.Story

class StoryAdapter :
    ListAdapter<Story, StoryAdapter.StoryViewHolder>(DiffCallback) {
    var onItemClick: ((Story) -> Unit)? = null

    inner class StoryViewHolder(var binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listStoryItem: Story?) {
            binding.apply {
                tvItemName.text = listStoryItem?.name
                story = listStoryItem
                executePendingBindings()
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
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

    companion object DiffCallback : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }

    }

}