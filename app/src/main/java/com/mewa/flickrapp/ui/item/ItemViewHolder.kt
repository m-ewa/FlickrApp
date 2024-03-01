package com.mewa.flickrapp.ui.item

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.mewa.flickrapp.R
import com.mewa.flickrapp.databinding.ViewHolderItemBinding

class ItemViewHolder(
    private val binding: ViewHolderItemBinding,
    private val clickListener: (position: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            clickListener(bindingAdapterPosition)
        }
    }

    fun bind(cat: ItemUiState) = with(binding) {
        val radius = root.context.resources.getDimension(R.dimen.rounded_corner_radius)
        image.load(cat.imageUrl) {
            transformations(RoundedCornersTransformation(radius))
        }
        description.text = cat.description
    }
}