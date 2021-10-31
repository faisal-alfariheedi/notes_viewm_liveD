package com.example.notes_viewM_liveD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Note")
class Note(@PrimaryKey(autoGenerate = true)
           @ColumnInfo(name = "id") var id:Int=0,
           @ColumnInfo(name = "note")var note:String=""){

}