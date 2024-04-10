package com.mis.route.chatapp.ui.home.fragments.myrooms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mis.route.chatapp.R
import com.mis.route.chatapp.databinding.RoomItemBinding
import com.mis.route.chatapp.model.Room

class RoomsAdapter(private var roomsList: List<Room>, val onRoomClicked: (Int, Room) -> Unit) :
    Adapter<RoomsAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = DataBindingUtil.inflate<RoomItemBinding>(
            LayoutInflater.from(parent.context), R.layout.room_item, parent, false
        )
        return RoomViewHolder(binding)
    }

    override fun getItemCount(): Int = roomsList.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(position, roomsList[position])
    }

    fun updateRooms(newRoomsList: List<Room>) {
        roomsList = newRoomsList
        notifyDataSetChanged()
    }

    inner class RoomViewHolder(var binding: RoomItemBinding) : ViewHolder(binding.root) {
        fun bind(position: Int, room: Room) {
            binding.room = room
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onRoomClicked.invoke(position, room)
            }

        }
    }
}