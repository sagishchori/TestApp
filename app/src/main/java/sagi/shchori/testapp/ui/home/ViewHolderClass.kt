package sagi.shchori.testapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import sagi.shchori.testapp.databinding.ViewHolderItemBinding
import sagi.shchori.testapp.ui.home.model.ModelClass

class ViewHolderClass(private val binding: ViewHolderItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ModelClass) {
        binding.title.text = "Retrive Title from data object"

        binding.description.text = "Retrive Description from data object"

        binding.imageView.load("") // Load a URL into the imageView using Coil
    }
}