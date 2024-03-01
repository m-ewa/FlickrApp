package com.mewa.flickrapp.ui.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mewa.flickrapp.databinding.ViewHolderItemBinding

class ItemAdapter(
    private val clickListener: (ItemUiState) -> Unit
) : ListAdapter<ItemUiState, ItemViewHolder>(CatDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(
            binding = binding,
            clickListener = { position ->
                clickListener(getItem(position))
            }
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CatDiffer : DiffUtil.ItemCallback<ItemUiState>() {

        override fun areItemsTheSame(oldItem: ItemUiState, newItem: ItemUiState): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: ItemUiState, newItem: ItemUiState): Boolean {
            return oldItem == newItem
        }
    }
}