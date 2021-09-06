package com.antonchuraev.becomebetter.database

import androidx.room.*
import com.antonchuraev.becomebetter.dataClasses.Goal


@Dao
public interface GoalDao {

    @Query("SELECT * FROM goal")
    fun getGoals(): List<Goal>

    @Insert
    fun insert(goal: Goal)

    @Update
    fun update(goal: Goal)

    @Delete
    fun delete(goal: Goal)

}