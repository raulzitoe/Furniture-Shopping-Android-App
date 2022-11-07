package com.group.furniture_shopping_android_app.login_signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.group.furniture_shopping_android_app.R
import com.group.furniture_shopping_android_app.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        with(binding) {
            tvSignin.setOnClickListener { navController.navigate(R.id.action_signUpFragment_to_loginFragment) }
            btnSignup.setOnClickListener {
                viewModel.createNewUser(
                    etSignupName.text.toString(),
                    etSignupEmail.text.toString(),
                    etSignupPassword.text.toString()
                )
                navController.navigate(R.id.action_signUpFragment_to_loginFragment)
            }
        }
    }

}