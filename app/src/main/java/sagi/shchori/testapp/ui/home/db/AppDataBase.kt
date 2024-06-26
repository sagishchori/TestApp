package sagi.shchori.testapp.ui.home.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

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