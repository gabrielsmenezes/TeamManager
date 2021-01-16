package com.menezes.teammanager.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.menezes.teammanager.data.db.entity.PlayerEntity

@Dao
interface PlayerDAO {
    @Insert
    suspend fun insert(player: PlayerEntity): Long

    @Update
    suspend fun update(player: PlayerEntity)

    @Query("delete from player where id= :id")
    suspend fun delete(id: Long)

    @Query("delete from player")
    suspend fun deleteAll()

    @Query("select * from player")
    suspend fun getAll(): List<PlayerEntity>

}