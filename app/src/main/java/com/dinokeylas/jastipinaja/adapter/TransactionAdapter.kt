package com.dinokeylas.jastipinaja.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dinokeylas.jastipinaja.DetailTransactionActivity
import com.dinokeylas.jastipinaja.R
import com.dinokeylas.jastipinaja.model.Transaction
import com.dinokeylas.jastipinaja.utils.DateUtils
import com.dinokeylas.jastipinaja.utils.TransactionExample

class TransactionAdapter(
    private val context: Context,
    private val transactionList: ArrayList<Transaction>
) :
    RecyclerView.Adapter<TransactionAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_item_transaction, parent, false
            )
        )
    }

    override fun getItemCount(): Int = transactionList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.tvDate.text =
            "Tanggal: " + DateUtils.getStringFormatedDate(transactionList[position].createAt)
        holder.tvUserName.text = "User: " + transactionList[position].jastiper
        holder.tvProductName.text = "Nama Produk: " + transactionList[position].post.product.name
        holder.tvTransactionType.text = "Jenis Transaksi: Titip Beli"
        holder.tvTransactionProgress.text = "Progress: " + transactionList[position].progress
        holder.cardView.setOnClickListener(onClickListener(position))
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val tvDate: TextView = itemView.findViewById(R.id.tv_transaction_date)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_user_name)
        val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvTransactionType: TextView = itemView.findViewById(R.id.tv_transaction_type)
        val tvTransactionProgress: TextView =
            itemView.findViewById(R.id.tv_transaction_progress)
    }

    /*this method must refactor to view task*/
    private fun onClickListener(position: Int): View.OnClickListener {
        return View.OnClickListener {
            // Toast.makeText(context, vegetableList[position].toString(), Toast.LENGTH_SHORT).show()
            moveToTransactionDetail(transactionList[position])
        }
    }

    private fun moveToTransactionDetail(transaction: Transaction) {
        context.startActivity(
            Intent(context, DetailTransactionActivity::class.java).putExtra(
                DetailTransactionActivity.TRANSACTION_ID,
                transaction.tranId
            )
        )
    }

}
