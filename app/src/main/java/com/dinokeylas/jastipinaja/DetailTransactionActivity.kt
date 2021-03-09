package com.dinokeylas.jastipinaja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dinokeylas.jastipinaja.adapter.ProgressAdapter
import com.dinokeylas.jastipinaja.model.Transaction
import com.dinokeylas.jastipinaja.utils.Constant.Collections.Companion.TRANSACTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detail_transaction.*

class DetailTransactionActivity : AppCompatActivity() {
    companion object {
        val TRANSACTION_ID = "transactionId"
    }

    private var transactionId = ""
    private var transaction: Transaction? = Transaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaction)
        init()
    }

    private fun init() {
        initObject()
        initUI()
        eventUI()
    }

    private fun initObject() {
        transactionId = intent.getStringExtra(TRANSACTION_ID)
        loadTransactionData()
    }

    private fun initUI() {
        tvNamaBarang.text = transaction?.post?.product?.name ?: ""
        tvKategoriBarang.text = transaction?.post?.product?.category ?: ""
        tvDeskripsiBarang.text = transaction?.post?.product?.description ?: ""
        tvJumlahBarang.text = transaction?.qty.toString()
        tvTotalTransaksiBarang.text = transaction?.totalPay.toString()
        tvProgress.text = transaction?.progress ?: ""
        tvLokasi.text = transaction?.purchaserAddress ?: ""
        Glide.with(this).load(transaction?.post?.product?.imageUrl).into(ivBarang)

//        val recyclerView: RecyclerView = findViewById(R.id.rvProgress)
//        val progressAdapter = ProgressAdapter(this, transaction?.progress!!)
//        recyclerView.setHasFixedSize(false)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = progressAdapter

        //init: just for demo
        tvStatus1.text = "Status"
        tvStatus2.text = "Status"
        tvStatus3.text = "Status"
        tvStatus4.text = "Status"
        tvTaggal1.text = "15 Sep 2020"
        tvTagga2.text = "15 Sep 2020"
        tvDescStatus1.text = "Dipesan"
        tvDescStatus2.text = "Sudah Dibayar"
        tvDescStatus3.text = "Sedang Menuju Rumah Anda"
        tvDescStatus4.text = "Selesai"
    }

    private fun eventUI() {

    }

    private fun loadTransactionData() {
        FirebaseFirestore.getInstance().collection(TRANSACTION).document(transactionId).get()
            .addOnSuccessListener {
                transaction = it.toObject(Transaction::class.java)
                initUI()
            }.addOnFailureListener {
                Log.d("TRANSACTION-DATA", it.message)
            }
    }
}