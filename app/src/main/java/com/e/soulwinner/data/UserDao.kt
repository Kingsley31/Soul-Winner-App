package com.e.soulwinner.data

import com.e.soulwinner.models.User
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * from users WHERE id = :userId")
    fun getUser(userId:Int): LiveData<User>

    @Update
    fun updateUser(user: User)
}