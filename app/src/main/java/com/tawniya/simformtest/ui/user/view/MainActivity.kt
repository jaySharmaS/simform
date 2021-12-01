package com.tawniya.simformtest.ui.user.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tawniya.simformtest.databinding.ActivityMainBinding
import com.tawniya.simformtest.datasource.network.NetworkUtils
import com.tawniya.simformtest.model.UserBasicInfo
import com.tawniya.simformtest.ui.user.UserAdapter
import com.tawniya.simformtest.ui.user.UsersViewModel
import com.tawniya.simformtest.ui.user.UsersViewModelFactory
import com.tawniya.simformtest.ui.user.details.UserDetailsActivity

class MainActivity : AppCompatActivity(), UserAdapter.OnUserSelectListener {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            UsersViewModelFactory(application)
        )[UsersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (NetworkUtils.isOnline(applicationContext)) {
            viewModel.refresh()
        }

        val adapter = UserAdapter()
        adapter.addOnUserSelectListener(this)

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        binding.rvUser.adapter = adapter

        viewModel.users.observe(this) {
            adapter.submitData(it)
        }

    }

    override fun onUserSelected(user: UserBasicInfo) {
        startActivity(Intent(this, UserDetailsActivity::class.java).apply {
            putExtra(UserDetailsActivity.KEY_USER_ID, user.id)
        })
    }
}