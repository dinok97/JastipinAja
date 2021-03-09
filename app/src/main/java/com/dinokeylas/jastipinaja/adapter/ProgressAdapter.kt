package com.dinokeylas.jastipinaja.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dinokeylas.jastipinaja.R
import com.dinokeylas.jastipinaja.utils.Constant.TransactionProgress.Companion.ON_THE_WAY
import com.dinokeylas.jastipinaja.utils.Constant.TransactionProgress.Companion.ORDER
import com.dinokeylas.jastipinaja.utils.Constant.TransactionProgress.Companion.PAYED
import com.dinokeylas.jastipinaja.utils.DateUtils
import java.util.*

class ProgressAdapter(
    private val context: Context,
    private val progress: String
) :
    RecyclerView.Adapter<ProgressAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProgressAdapter.ItemViewHolder {
        return ProgressAdapter.ItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_progress, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: ProgressAdapter.ItemViewHolder, position: Int) {
        if (progress == ORDER) {
            if (position < 1) {
                holder.tvDeliveryDate.text =
                    DateUtils.getStringFormatedDate(Calendar.getInstance().time)
            } else {
                holder.tvDeliveryDate.visibility = View.GONE
            }
        } else if (progress == PAYED) {
            if (position < 2) {
                holder.tvDeliveryDate.text =
                    DateUtils.getStringFormatedDate(Calendar.getInstance().time)
            } else {
                holder.tvDeliveryDate.visibility = View.GONE
            }
        } else if (progress == ON_THE_WAY) {
            if (position < 3) {
                holder.tvDeliveryDate.text =
                    DateUtils.getStringFormatedDate(Calendar.getInstance().time)
            } else {
                holder.tvDeliveryDate.visibility = View.GONE
            }
        } else {
            holder.tvDeliveryDate.text =
                DateUtils.getStringFormatedDate(Calendar.getInstance().time)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDeliveryDate: TextView = itemView.findViewById(R.id.tvDeliveryDate)
    }
}