package com.antonchuraev.becomebetter.database

import androidx.room.*
import com.antonchuraev.becomebetter.dataClasses.Goal


@Dao
public interface GoalDao {

    @Query("SELECT * FROM goal")
    fun getGoals(): List<Goal>

    @Query("SELECT * FROM goal WHERE isActive = :isActive ")
    fun getGoals(isActive:Boolean): List<Goal>

    @Insert
    fun insert(goal: Goal)

    @Update
    fun update(goal: Goal)

    @Delete
    fun delete(goal: Goal)

}