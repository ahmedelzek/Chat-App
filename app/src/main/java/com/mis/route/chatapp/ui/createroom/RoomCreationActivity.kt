package com.mis.route.chatapp.ui.createroom

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.databinding.ActivityRoomCreationBinding

class RoomCreationActivity : BaseActivity<RoomCreationViewModel, ActivityRoomCreationBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeToLiveData() {
        super.observeToLiveData()
        viewModel.events.observe(this) {
            Log.e("Activity", "Finishing activity")
            it?.let {
                when (it) {
                    RoomCreationEvents.RoomCreated -> {
                        finish()
                    }
                }
            }
        }
    }

    override fun initViewModel(): RoomCreationViewModel =
        ViewModelProvider(this)[RoomCreationViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.activity_room_creation
}