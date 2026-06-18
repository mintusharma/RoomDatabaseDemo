package com.geekdroid.roomdemo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(studentEntity: StudentEntity)

    @Update
    suspend fun  update(studentEntity: StudentEntity)

    @Query("Select * from student_table")
    fun getAllStudent(): Flow<List<StudentEntity>>

    @Query("Delete from student_table where id = :id")
    suspend fun delete(id: Int)

}