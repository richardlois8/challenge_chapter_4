package com.rich.challenge4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rich.challenge4.database.Notes
import com.rich.challenge4.database.NotesTakingDB
import com.rich.challenge4.database.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(app : Application) : AndroidViewModel(app) {
    lateinit var allUser : MutableLiveData<List<User>>

    init {
        allUser = MutableLiveData()
        getAllUser()
    }

    fun getAllUserObserver() : MutableLiveData<List<User>> {
        return allUser
    }

    fun getAllUser() {
        GlobalScope.launch {
            val userDAO = NotesTakingDB.getInstance((getApplication()))!!.userDao()
            val listNotes = userDAO.getAllUser()
            allUser.postValue(listNotes)
        }
    }

    fun insertUser(user : User){
        GlobalScope.async {
            val userDAO = NotesTakingDB.getInstance(getApplication())?.userDao()!!
            userDAO.insertUser(user)
        }
    }
}