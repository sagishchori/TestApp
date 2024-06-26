package sagi.shchori.testapp.ui.home.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sagi.shchori.testapp.ui.home.model.ModelClass

@Database(entities = [ModelClass::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDB(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "appDataBase"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}