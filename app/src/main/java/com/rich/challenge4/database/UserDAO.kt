package com.rich.challenge4.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Insert
    fun insertUser(user: User)
}