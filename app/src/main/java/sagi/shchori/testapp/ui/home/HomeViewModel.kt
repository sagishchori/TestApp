package sagi.shchori.testapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import sagi.shchori.testapp.Result
import sagi.shchori.testapp.ui.UiState
import sagi.shchori.testapp.ui.home.model.ModelClass
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

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

    private val _itemsList = MutableLiveData<List<ModelClass>>().apply {
        value = arrayListOf()
    }
    val itemsList: LiveData<List<ModelClass>> = _itemsList

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(UiState.LOADING)

            repository.getData().collect {  result ->

                // Failure
                if (result.isEmpty()) {
                    _itemsList.postValue(emptyList())

                    _uiState.postValue(UiState.FAILURE)
                }
                // Success
                else {
                    _itemsList.postValue(result)

                    _uiState.postValue(UiState.SUCCESS)
                }
            }
        }
    }
}