package com.geekdroid.roomdemo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoomViewModel(): ViewModel() {

    private var _dao: StudentDao?=null

    fun init(context: Context){
        _dao= AppDatabase.getInstance(context).getStudentDao()
    }

    fun getAllStudent()= _dao?.getAllStudent()

    fun save(name: String, age: String)= viewModelScope.launch {
        val studentEntity= StudentEntity(name = name, age = age)
        _dao?.save(studentEntity)
    }

    fun update(name: String,age: String, id: Int)= viewModelScope.launch {
        val studentEntity= StudentEntity(id, name, age)
        _dao?.update(studentEntity)
    }

    fun delete(id: Int)= viewModelScope.launch {
        _dao?.delete(id)
    }
}