package com.example.login.data.adapters

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.models.Users
import com.example.login.ui.MainActivity
import com.example.login.util.MyMessages

open class MyViewHolderUser(itemView: View): RecyclerView.ViewHolder(itemView) {
    lateinit var usename: TextView

    fun bind(use: Users) {

        usename = itemView.findViewById(R.id.UsernameL)

        usename.text = use?.username


        itemView.setOnClickListener {
            MyMessages.toast(itemView.context, use?.username.toString())
            val intent = Intent(itemView.context, MainActivity::class.java)
            intent.putExtra("per_id", usename.id)
            itemView.context.startActivity(intent)
        }
    }
}