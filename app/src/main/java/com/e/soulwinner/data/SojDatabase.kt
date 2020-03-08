package com.e.soulwinner.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.soulwinner.models.Soul
import com.e.soulwinner.models.User

@Database(entities = arrayOf(User::class, Soul::class), version = 1, exportSchema = false)
public abstract class SojDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun soulDao(): SoulDao

    companion object {
        val DATABASE_NAME:String="sojdb"
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SojDatabase? = null

        fun getDatabase(context: Context): SojDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SojDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}