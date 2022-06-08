package com.example.mywallet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mywallet.data.Transaction
import com.example.mywallet.databinding.ListBinding

class TransactionListAdapter :
    ListAdapter<Transaction, TransactionListAdapter.TransactionViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {
        return TransactionViewHolder(
            ListBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: TransactionViewHolder,
        position: Int
    ) {
        val current = getItem(position)
        holder.bind(current)
    }

    class TransactionViewHolder(private var binding: ListBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(transaction: Transaction) {
            binding.apply {
                textTitle.text = "Title: " + transaction.transactionName
                textAmount.text = "Amount: " + transaction.amount.toString()
                textBalance.text = "Balance: Rp. " + transaction.balance.toString()
                textDate.text = "Date: " + transaction.date
                textNotes.text = "Note: \n" + transaction.notes
                textInOut.text = transaction.inOut
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem.transactionName == newItem.transactionName
            }

        }
    }

}