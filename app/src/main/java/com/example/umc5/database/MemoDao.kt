package com.example.umc5.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.umc5.DataObj

@Dao
interface MemoDao{
    @Insert
    fun insert(memo:MemoData)

    @Delete
    fun delete(memo:MemoData)

    @Update
    fun update(memo: MemoData)

    @Query("DELETE FROM memoTable WHERE pos = :pos")
    fun delItem(pos : Int)

    @Query("SELECT *FROM memoTable")
    fun restart() : List<DataObj>
}