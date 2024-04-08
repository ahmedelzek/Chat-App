package com.mis.route.chatapp.ui.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentLoginBinding
import com.mis.route.chatapp.ui.home.HomeActivity


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vm = viewModel
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this)[LoginViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun observeToLiveData() {
        super.observeToLiveData()
        viewModel.events.observe(viewLifecycleOwner) {
            when (it) {
                is LoginScreenEvents.NavigateToHomeEvent -> {
                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                }

                is LoginScreenEvents.NavigateToRegisterEvent -> {
                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                }

                else -> {

                }
            }
        }
    }
}