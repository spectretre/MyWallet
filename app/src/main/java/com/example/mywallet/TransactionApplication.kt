package com.example.mywallet

import android.app.Application
import com.example.mywallet.data.TransactionRoomDatabase

class TransactionApplication: Application() {

    val database: TransactionRoomDatabase by lazy { TransactionRoomDatabase.getDatabase(this) }

}