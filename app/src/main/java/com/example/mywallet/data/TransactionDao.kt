package com.example.mywallet.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transaction: Transaction)

    /**@Delete
    suspend fun delete(transaction: Transaction)
**/
    @Query("SELECT * FROM `transaction` ORDER BY id DESC")
    fun getAll(): Flow<List<Transaction>>

}