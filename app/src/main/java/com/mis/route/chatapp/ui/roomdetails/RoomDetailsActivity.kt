package com.mis.route.chatapp.ui.roomdetails

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mis.route.chatapp.databinding.ActivityRoomDetailsBinding
import com.mis.route.chatapp.ui.chat.ChatActivity

class RoomDetailsActivity : AppCompatActivity() {
    private var _binding: ActivityRoomDetailsBinding? = null
    private val binding: ActivityRoomDetailsBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRoomDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openRoomBtn.setOnClickListener { navigateToChat() }
    }

    private fun navigateToChat() {
        startActivity(Intent(this, ChatActivity::class.java))
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}