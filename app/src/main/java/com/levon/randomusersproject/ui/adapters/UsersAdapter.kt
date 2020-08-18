package com.levon.randomusersproject.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.levon.randomusersproject.R
import com.levon.randomusersproject.data.models.user.UserModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UsersAdapter(private val context: Context): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private val usersList: ArrayList<UserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view: View = LayoutInflater
            .from(context)
            .inflate(R.layout.users_item_style, parent, false)

        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usersList.size * 10
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentUser = usersList[position / 10].results[position % 10]
        Picasso.get().load(currentUser.picture.medium).into(holder.userImage)
        holder.userName.setText(String.format("%s %s", currentUser.name.first, currentUser.name.last))
        holder.userNationality.setText(currentUser.location.country)
    }

    fun addNewUsers(userModel: UserModel) {
        usersList.add(userModel)
        notifyDataSetChanged()
    }

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.findViewById<CircleImageView>(R.id.user_item_image)
        val userName = itemView.findViewById<MaterialTextView>(R.id.user_full_name)
        val userNationality = itemView.findViewById<MaterialTextView>(R.id.user_nationality)
    }
}