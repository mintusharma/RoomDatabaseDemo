package com.geekdroid.roomdemo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "student_table")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val id : Int=0,
    val name: String,
    val age: String

)