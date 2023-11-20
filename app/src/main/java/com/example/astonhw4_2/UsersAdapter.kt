package com.example.astonhw4_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UsersAdapter(private var userList: MutableList<User>, private val onItemClick: (User) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun newUser(newUser: User){
        val i = newUser.id.toInt()
        userList[i] = newUser
        notifyItemChanged(i)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userPhoto: ImageView = itemView.findViewById(R.id.userPhoto)
        private val userName: TextView = itemView.findViewById(R.id.userName)
        private val userLastname: TextView = itemView.findViewById(R.id.userLastName)
        private val userPhoneNumber: TextView = itemView.findViewById(R.id.userPhoneNumber)

        fun bind(user: User) {
            Glide.with(itemView)
                .load(user.photoUrl)
                .error(R.drawable.ic_launcher_background)
                .into(userPhoto)
            userName.text = user.firstName
            userLastname.text = user.lastName
            userPhoneNumber.text = user.phoneNumber

            itemView.setOnClickListener {
                onItemClick(user)
            }
        }
    }
}