package com.mis.route.chatapp.ui.home.fragments.myrooms

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentMyRoomsBinding
import com.mis.route.chatapp.model.Room
import com.mis.route.chatapp.ui.chat.ChatActivity
import com.mis.route.chatapp.ui.home.fragments.myrooms.adapter.RoomsAdapter

class MyRoomsFragment : BaseFragment<MyRoomsViewModel, FragmentMyRoomsBinding>(),
    RoomsAdapter.OnDeleteClickListener {
    private lateinit var adapter: RoomsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initRoomsRecyclerView()
    }

    private fun initRoomsRecyclerView() {
        adapter = RoomsAdapter(listOf()) { room ->
            val intent = Intent(activity, ChatActivity::class.java)
            intent.putExtra(ChatActivity.ROOM_KEY, room)
            startActivity(intent)
        }
        binding.roomsRecyclerView.adapter = adapter
        adapter.setOnDeleteClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshRooms()
    }

    override fun observeToLiveData() {
        super.observeToLiveData()
        viewModel.roomsLiveData.observe(this) {
            adapter.updateRooms(it)
        }
    }

    override fun initViewModel(): MyRoomsViewModel =
        ViewModelProvider(this)[MyRoomsViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_my_rooms
    override fun onDeleteClick(room: Room) {
        viewModel.delete(room)
    }
}