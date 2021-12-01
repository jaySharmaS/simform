package com.tawniya.simformtest.ui.user.details

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tawniya.simformtest.R
import com.tawniya.simformtest.databinding.ActivityUserDetailsBinding
import com.tawniya.simformtest.datasource.network.GlideApp

class UserDetailsActivity: AppCompatActivity() {

    companion object {
        const val KEY_USER_ID = "user_id"
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            UsersViewModelFactory(intent.getLongExtra(KEY_USER_ID, -1), application)
        )[UserDetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.userDetails.observe(this) { details ->
            GlideApp.with(this)
                .load(details.profileImage.large)
                .into(binding.ivUser)

            binding.collapsingToolbarLayout.title = getString(R.string.user_full_name, details.nameInfo.title, details.nameInfo.first, details.nameInfo.last)

            binding.lblUserDetailsNationality.text = getString(R.string.user_nationality, details.nationality)
            binding.lblUserDetailsLocation.text = getString(
                R.string.user_location,
                details.location.street.number,
                details.location.street.name,
                details.location.city,
                details.location.state,
                details.location.country,
            )

        }

        

    }

}