package com.menezes.teammanager.repository

import com.menezes.teammanager.data.db.entity.PlayerEntity
import java.util.*

interface PlayerRepository {
    suspend fun insertPlayer(
        name: String,
        purchasePrice: Float,
        purchaseDate: String,
        sellPrice: Float,
        sellDate: String
    ): Long

    suspend fun updatePlayer(
        id: Long,
        name: String,
        purchasePrice: Float,
        purchaseDate: String,
        sellPrice: Float,
        sellDate: String
    )

    suspend fun deletePlayer(id: Long)

    suspend fun deleteAllPlayers()

    suspend fun getAllPlayers(): List<PlayerEntity>
}