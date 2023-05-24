package com.example.umc5.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memoTable")
data class MemoData(var txt:String,var pos:Int ) {
    @PrimaryKey(autoGenerate = true) var id:Int=0
}