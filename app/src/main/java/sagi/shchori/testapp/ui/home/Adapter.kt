package sagi.shchori.testapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sagi.shchori.testapp.databinding.ViewHolderItemBinding
import sagi.shchori.testapp.ui.home.model.ModelClass
import javax.inject.Inject

class Adapter @Inject constructor() : RecyclerView.Adapter<ViewHolderClass>() {

    private val itemsList = arrayListOf<ModelClass>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val binding = ViewHolderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolderClass(binding)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val data = itemsList[position]
        holder.bind(data)
    }

    fun updateData(data: List<ModelClass>) {
        itemsList.clear()
        itemsList.addAll(data)
        notifyDataSetChanged()
    }
}