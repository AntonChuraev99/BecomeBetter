package com.antonchuraev.becomebetter.database

import androidx.room.*
import com.antonchuraev.becomebetter.dataClasses.Goal


@Dao
public interface GoalDao {

    @Query("SELECT * FROM goal WHERE isActive = :isActive ")
    fun getGoals(isActive:Boolean): List<Goal>

    @Insert
    fun insert(employee: Goal)

    @Update
    fun update(employee: Goal)

    @Delete
    fun delete(employee: Goal)

}