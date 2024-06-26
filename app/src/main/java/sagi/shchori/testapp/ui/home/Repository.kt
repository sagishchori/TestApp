package sagi.shchori.testapp.ui.home

import android.content.Context
import androidx.lifecycle.asLiveData
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import sagi.shchori.testapp.ModelClassService
import sagi.shchori.testapp.NetworkClient
import sagi.shchori.testapp.Result
import sagi.shchori.testapp.ui.home.db.AppDao
import sagi.shchori.testapp.ui.home.db.AppDataBase
import sagi.shchori.testapp.ui.home.model.ModelClass
import javax.inject.Inject

class Repository @Inject constructor(@ActivityContext val context: Context) {

    private val appDao: AppDao by lazy {
        AppDataBase.getDB(context).appDao()
    }

    suspend fun getData(): Flow<List<ModelClass>> {

        // Try to fetch items from DB
        val dataFromDB = appDao.getAllItems()

        // If items from DB is empty try to fetch data from network
        if (dataFromDB.asLiveData().value == null) {

            // Trying to fetch data from network
            val dataFromNetwork = getDataFromNetwork()

            // If data from network fetched we will save it in DB first
            if (dataFromNetwork.isNotEmpty()) {

                // Saving the data into DB should reflect on the UI on update
                saveDataFromNetwork(dataFromNetwork)
            }
        }

        return dataFromDB
    }

    private suspend fun saveDataFromNetwork(dataFromNetwork: List<ModelClass>) {
        appDao.insertAll(dataFromNetwork)
    }

    private suspend fun getDataFromNetwork(): List<ModelClass> {
        NetworkClient.createService(ModelClassService::class.java)?.getModelClass()?.let {
            return listOf(
                it
            )
        }

        return emptyList() // Need to implement the call to network to fetch the data
    }
}