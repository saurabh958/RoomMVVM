package com.example.roomdatasample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.roomdatasample.db.RoomAppDb
import com.example.roomdatasample.db.UserEntity

class MainActivityViewModel(app:Application): AndroidViewModel(app)
{
    var allUsers: MutableLiveData<List<UserEntity>> = MutableLiveData()

    init {

        getAllUsers()

    }

    fun getAllUsersObserver():MutableLiveData<List<UserEntity>>{
        return allUsers
    }

    fun getAllUsers(){
        val userDao = RoomAppDb.getAppDatabAse((getApplication()))?.userDao()
        val list = userDao?.getAllUserinfo()

        allUsers.postValue(list)

    }

    fun insertUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabAse((getApplication()))?.userDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }

    fun updateUserInfo(entity: UserEntity)
    {
        val userDao = RoomAppDb.getAppDatabAse((getApplication()))?.userDao()
        userDao?.updateUser(entity)
        getAllUsers()
    }

    fun deleteUserInfo(entity: UserEntity)
    {
        val userDao = RoomAppDb.getAppDatabAse((getApplication()))?.userDao()
        userDao?.deleteUser(entity)
        getAllUsers()
    }








}