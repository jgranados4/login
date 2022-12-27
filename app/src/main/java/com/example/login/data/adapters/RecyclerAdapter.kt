package com.example.login.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.models.Users

class RecyclerAdapter(val employeeList: List<Users>) : RecyclerView.Adapter<MyViewHolderUser>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderUser {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view  = layoutInflater.inflate(R.layout.activity_ventana_new, parent, false)
        val viewHolder = MyViewHolderUser(view)
        return viewHolder
    }
    override fun getItemCount(): Int {
        return employeeList?.size!!
    }
    override fun onBindViewHolder(holder: MyViewHolderUser, position: Int) {
        employeeList?.get(position)?.let { holder.bind(it) }
    }
}