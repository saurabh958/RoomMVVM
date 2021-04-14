package com.example.roomdatasample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatasample.db.UserEntity

class RecyclerAdapter(val listener: RowClickListener): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var items = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>)
    {
        this.items=data

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_row,parent,false)
        return MyViewHolder(inflater,listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener{
            listener.onItemClickListener(items[position])
        }
       holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view)
    {
        val tvname = view.findViewById<TextView>(R.id.textname)
        val deleteimgbtn=view.findViewById<ImageButton>(R.id.delete_button)
        fun bind(data: UserEntity){
            tvname.text = data.namez
            deleteimgbtn.setOnClickListener{
                listener.onDeleteUserClickListener(data)

            }

        }

    }

    interface RowClickListener{
        fun onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user:UserEntity)
    }


}