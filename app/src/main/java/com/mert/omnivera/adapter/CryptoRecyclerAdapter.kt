package com.mert.omnivera.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mert.omnivera.R
import com.mert.omnivera.model.CryptoModel
import kotlinx.android.synthetic.main.crypto_row.view.*

class CryptoRecyclerAdapter(private val cryptoList : ArrayList<CryptoModel>, private val listener : Listener):RecyclerView.Adapter<CryptoRecyclerAdapter.RowHolder>() {


    interface Listener {
        fun onItemClick(cryptoModel: CryptoModel)
    }




    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel: CryptoModel, position: Int, listener: Listener) {

            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }

            itemView.text_name.text = cryptoModel.currency
            itemView.text_price.text = cryptoModel.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_row,parent,false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],position,listener)
    }


}