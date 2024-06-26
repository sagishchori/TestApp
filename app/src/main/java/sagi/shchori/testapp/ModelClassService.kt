package sagi.shchori.testapp

import retrofit2.http.GET
import sagi.shchori.testapp.ui.home.model.ModelClass

interface ModelClassService {

    @GET("LINK TO DOWNLOAD")
    suspend fun getModelClass(): ModelClass?
}