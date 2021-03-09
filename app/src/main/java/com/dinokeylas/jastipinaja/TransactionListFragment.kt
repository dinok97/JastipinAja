package com.dinokeylas.jastipinaja

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dinokeylas.jastipinaja.adapter.TransactionAdapter
import com.dinokeylas.jastipinaja.model.Transaction
import com.dinokeylas.jastipinaja.utils.Constant.Collections.Companion.TRANSACTION
import com.dinokeylas.jastipinaja.utils.GenerateData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TransactionListFragment : Fragment() {

    var userId = ""
    var transactionList = ArrayList<Transaction>()
    lateinit var recyclerView: RecyclerView
    lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_list, container, false)
        recyclerView = view.findViewById(R.id.rv_transaction)

        initObject()
        eventUI()

        return view
    }

    private fun initObject() {
        userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        loadTransactionData()
    }

    private fun initUI() {
        transactionAdapter = TransactionAdapter(context!!, transactionList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = transactionAdapter
    }

    private fun eventUI() {

    }

    private fun loadTransactionData() {
        FirebaseFirestore.getInstance().collection(TRANSACTION).whereEqualTo("jastiper", userId)
            .get().addOnSuccessListener { documents ->
                for (document in documents) {
                    val transaction: Transaction = document.toObject(Transaction::class.java)
                    transaction.tranId = document.id
                    transactionList.add(transaction)
                }
                initUI()
            }.addOnFailureListener {
                Log.d("TRANSACTION DATA", it.message)
            }
    }

}
