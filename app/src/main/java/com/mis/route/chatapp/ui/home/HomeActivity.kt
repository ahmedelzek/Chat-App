package com.mis.route.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.databinding.ActivityHomeBinding
import com.mis.route.chatapp.ui.auth.AuthActivity
import com.mis.route.chatapp.ui.createroom.RoomCreationActivity
import com.mis.route.chatapp.ui.home.adapter.RoomsViewPagerAdapter

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initRoomsViewPager()
    }

    override fun initViewModel(): HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun observeToLiveData() {
        super.observeToLiveData()
        onItemNavClicked()
        viewModel.events.observe(this) {
            it.let {
                when (it) {
                    is HomeScreenEvents.NavigateToLoginEvent -> {
                        startActivity(Intent(this, AuthActivity::class.java))
                    }

                    is HomeScreenEvents.NavigateToCreateRoomEvent -> {
                        startActivity(Intent(this, RoomCreationActivity::class.java))
                    }
                }
            }
        }
    }

    private fun onItemNavClicked() {
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout -> {
                    viewModel.logout()
                    finish()
                    true
                }

                else -> false
            }
        }
    }

    private fun initRoomsViewPager() {
        val adapter = RoomsViewPagerAdapter(this)
        binding.roomsViewPager.adapter = adapter
        TabLayoutMediator(binding.roomsTabLayout, binding.roomsViewPager) { tab, position ->
            val tabTitles =
                resources?.getStringArray(R.array.rooms_fragments_titles) ?: emptyArray()
            tab.text = tabTitles[position]
        }.attach()
    }

}