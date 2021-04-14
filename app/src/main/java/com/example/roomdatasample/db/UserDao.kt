package com.example.roomdatasample.db

import androidx.room.*


@Dao
interface UserDao
{
    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getAllUserinfo(): List<UserEntity>?

    @Insert
    fun insertUser(user: UserEntity?)

    @Delete
    fun deleteUser(user: UserEntity?)

    @Update
    fun updateUser(user: UserEntity?)



}