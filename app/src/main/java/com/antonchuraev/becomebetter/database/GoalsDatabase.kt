package com.antonchuraev.becomebetter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antonchuraev.becomebetter.dataClasses.Goal

@Database(entities = [Goal::class], version = 1)
public abstract class GoalsDatabase:RoomDatabase() {

    abstract fun goalsDao(): GoalDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GoalsDatabase? = null

        fun getDatabase(context: Context): GoalsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalsDatabase::class.java,
                    "goal_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}