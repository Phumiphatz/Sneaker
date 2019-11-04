package com.example.sneaky

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sneakers")
data class sneakyDatabaseModels(
    @PrimaryKey(autoGenerate = true)
    var sneakyId: Int = 0,

    @ColumnInfo(name = "name_category")
    var sneakyCategory: String,

    @ColumnInfo(name = "name_band")
    var sneakyBand: String,

    @ColumnInfo(name = "name_picture")
    var sneakypicture: String,

    @ColumnInfo(name = "name_detail")
    var sneakyDetail: String
)