package com.example.mywallet.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val transactionName: String,

    @ColumnInfo(name = "in/out")
    val inOut: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "balance")
    val balance: Int,

    @ColumnInfo(name = "notes")
    val notes: String
)