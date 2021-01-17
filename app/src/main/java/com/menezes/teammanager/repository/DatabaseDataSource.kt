package com.menezes.teammanager.repository

import com.menezes.teammanager.data.db.dao.PlayerDAO
import com.menezes.teammanager.data.db.entity.PlayerEntity
import java.util.*

class DatabaseDataSource(private val playerDAO: PlayerDAO) : PlayerRepository {
    override suspend fun insertPlayer(
        name: String,
        purchasePrice: Float,
        purchaseDate: String,
        sellPrice: Float,
        sellDate: String
    ): Long {
        val player = PlayerEntity(
            name = name,
            purchasePrice = purchasePrice,
            purchaceDate = purchaseDate,
            sellPrice = sellPrice,
            sellDate = sellDate
        )
        return playerDAO.insert(player)
    }

    override suspend fun updatePlayer(
        id: Long,
        name: String,
        purchasePrice: Float,
        purchaseDate: String,
        sellPrice: Float,
        sellDate: String
    ) {

        val player = PlayerEntity(
            id = id,
            name = name,
            purchasePrice = purchasePrice,
            purchaceDate = purchaseDate,
            sellPrice = sellPrice,
            sellDate = sellDate
        )

        playerDAO.update(player)

    }

    override suspend fun deletePlayer(id: Long) {
        playerDAO.delete(id)
    }

    override suspend fun deleteAllPlayers() {
        playerDAO.deleteAll()
    }

    override suspend fun getAllPlayers(): List<PlayerEntity> {
        return playerDAO.getAll()
    }
}