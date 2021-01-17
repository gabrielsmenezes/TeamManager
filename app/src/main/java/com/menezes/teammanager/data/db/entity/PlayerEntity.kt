package com.menezes.teammanager.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "player")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val purchasePrice: Float,
    val purchaceDate: String,
    val sellPrice: Float,
    val sellDate: String
)