package com.rich.challenge4.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val content : String
) : Parcelable
