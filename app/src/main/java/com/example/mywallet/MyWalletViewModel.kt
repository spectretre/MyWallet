package com.example.mywallet

import androidx.lifecycle.*
import com.example.mywallet.data.Transaction
import com.example.mywallet.data.TransactionDao
import kotlinx.coroutines.launch

class MyWalletViewModel(private val transactionDao: TransactionDao) : ViewModel() {


    val allTransaction: LiveData<List<Transaction>> = transactionDao.getAll().asLiveData()

    private fun insertTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionDao.insert(transaction)
        }
    }

    /**fun deleteTransaction(transaction: Transaction){
        viewModelScope.launch {
            transactionDao.delete(transaction)
        }
    }
**/
    private fun getNewTransactionEntry(
        transactionName: String,
        inOut: String,
        date: String,
        amount: String,
        balance: String,
        notes: String
    ): Transaction {
        return Transaction(
            transactionName = transactionName,
            inOut = inOut,
            date = date,
            amount = amount.toInt(),
            balance = balance.toInt(),
            notes = notes
        )
    }

    fun addNewTransaction(
        transactionName: String,
        inOut: String,
        date: String,
        amount: String,
        balance: String,
        notes: String
    ) {
        val newItem = getNewTransactionEntry(transactionName, inOut, date, amount, balance, notes)
        insertTransaction(newItem)
    }

    fun isEntryValid(
        transactionName: String,
        inOut: String,
        date: String,
        amount: String,
        balance: String,
        notes: String
    ): Boolean {
        if (transactionName.isBlank() || inOut.isBlank() || date.isBlank() || amount.isBlank() || balance.isBlank() || notes.isBlank()) {
            return false
        }
        return true
    }


    class MyWalletViewModelFactory(private val transactionDao: TransactionDao) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyWalletViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MyWalletViewModel(transactionDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }

    }
}