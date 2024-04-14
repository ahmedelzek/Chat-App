package com.mis.route.chatapp.ui.auth.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentRegisterBinding
import com.mis.route.chatapp.ui.home.HomeActivity


class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vm = viewModel
        super.onViewCreated(view, savedInstanceState)
    }

    override fun observeToLiveData() {
        super.observeToLiveData()
        viewModel.events.observe(viewLifecycleOwner) {
            it.let {
                when (it) {
                    is RegisterScreenEvents.NavigateToHomeEvent -> {
                        val intent = Intent(activity, HomeActivity::class.java)
                        startActivity(intent)
                    }

                    is RegisterScreenEvents.NavigateToLoginEvent -> {
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                }


            }
        }
    }

    override fun initViewModel(): RegisterViewModel =
        ViewModelProvider(this)[RegisterViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_register
}