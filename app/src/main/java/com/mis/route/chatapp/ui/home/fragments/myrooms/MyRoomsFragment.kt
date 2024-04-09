package com.mis.route.chatapp.ui.home.fragments.myrooms

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentMyRoomsBinding

class MyRoomsFragment : BaseFragment<MyRoomsViewModel, FragmentMyRoomsBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshRooms()
    }

    override fun initViewModel(): MyRoomsViewModel =
        ViewModelProvider(this)[MyRoomsViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_my_rooms

}