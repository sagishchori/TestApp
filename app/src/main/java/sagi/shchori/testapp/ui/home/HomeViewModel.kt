package sagi.shchori.testapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModel(private val repository: Repository) : ViewModel() {

    init {
        loadData()
    }

    companion object {
        fun provideFactory(
            repository: Repository
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                        return HomeViewModel(repository) as T
                    }

                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "No results to show"
    }
    val text: LiveData<String> = _text

    private val _itemsList = MutableLiveData<ArrayList<String>>().apply {
        value = arrayListOf()
    }

    val itemsList: LiveData<ArrayList<String>> = _itemsList

    private fun loadData() {

    }
}