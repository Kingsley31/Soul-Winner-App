package com.e.soulwinner.data

import com.e.soulwinner.models.Soul
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SoulDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSoul(soul: Soul)

    @Query("SELECT * from souls WHERE id = :soulId")
    fun getSoul(soulId:Int): LiveData<Soul>

    @Query("SELECT * from souls WHERE status = :following_id")
    fun getFollowingSouls(following_id:Int): LiveData<List<Soul>>

    @Query("SELECT * from souls WHERE status = :pending_id")
    fun getPendingSouls(pending_id:Int): LiveData<List<Soul>>

    @Query("SELECT * from souls WHERE status = :established_id")
    fun getEstablishedSouls(established_id:Int): LiveData<List<Soul>>

    @Update
    fun updateSoul(soul: Soul)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSouls(souls: List<Soul>)
}