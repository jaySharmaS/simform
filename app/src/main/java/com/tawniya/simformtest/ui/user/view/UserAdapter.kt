package com.tawniya.simformtest.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tawniya.simformtest.R
import com.tawniya.simformtest.databinding.LayoutRvUserBinding
import com.tawniya.simformtest.datasource.network.GlideApp
import com.tawniya.simformtest.model.UserBasicInfo

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private val users = mutableListOf<UserBasicInfo>()

    private var onUserSelectListener: OnUserSelectListener? = null

    fun addOnUserSelectListener(onUserSelectListener: OnUserSelectListener) {
        this.onUserSelectListener = onUserSelectListener
    }

    fun submitData(users: List<UserBasicInfo>) {
        val result = DiffUtil.calculateDiff(UserDiffCallback(this.users, users))
        this.users.clear()
        this.users.addAll(users)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(LayoutRvUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    inner class UserHolder(private val binding: LayoutRvUserBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(user: UserBasicInfo) {

            binding.lblUserName.text = itemView.context.getString(R.string.user_full_name, user.nameInfo.title, user.nameInfo.first, user.nameInfo.last)

            GlideApp.with(itemView)
                .load(user.profileImage.thumbnail)
                .placeholder(R.drawable.placeholder_user)
                .error(R.drawable.placeholder_user)
                .into(binding.ivUser)

            binding.lblUserNationality.text =  user.nationality
        }

        override fun onClick(v: View?) {
            onUserSelectListener?.onUserSelected(users[adapterPosition])
        }

    }

    interface OnUserSelectListener {
        fun onUserSelected(user: UserBasicInfo)
    }

}

class UserDiffCallback(
    private val oldItems: List<UserBasicInfo>,
    private val newItems: List<UserBasicInfo>,
): DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[oldItemPosition]
    }

}