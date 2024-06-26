package sagi.shchori.testapp.ui.home.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sagi.shchori.testapp.ui.home.model.ModelClass

@Dao
interface AppDao {

    @Query("SELECT * FROM modelTable")
    suspend fun getAllItems(): Flow<List<ModelClass>>

    @Insert
    suspend fun insertAll(items: List<ModelClass>)

    @Query("SELECT * FROM modelTable WHERE someField == :someField")
    fun getLocationData(someField: Int): List<ModelClass>

//    @Query("SELECT * FROM LocationDataTable WHERE tripCounter = :tripId")
//    fun getLocationDataByTripId(tripId: Int) : List<LocationData>
//
//    @Query("SELECT * FROM LocationDataTable ORDER BY uid DESC LIMIT 1")
//    fun getLastSavedLocation(): LocationData
//
//    @Insert
//    fun saveLocationData(vararg locationsData: LocationData): LongArray
//
//    @Insert
//    fun saveLocationsData(list: List<LocationData>): LongArray
//
//    @Delete
//    fun deleteLocation(locationData: LocationData)
//
//    @Delete
//    fun deleteLocations(vararg locationsData: LocationData)
//
//    @Query("DELETE FROM LocationDataTable")
//    fun deleteAll()
}