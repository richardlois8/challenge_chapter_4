package com.rich.challenge4.fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.rich.challenge4.R
import com.rich.challenge4.databinding.FragmentLoginBinding
import com.rich.challenge4.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var userVM : UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val PREFS_NAME = "dataUser"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        editor = sharedPreferences.edit()

        setListener()
    }

    fun setListener() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            verifyUser(email,password)
        }
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    fun verifyUser(email : String, password : String){
        userVM.getAllUser()
        val listAllUser = userVM.allUser.value!!
        var isFound = false
        if(listAllUser.size == 0){
            Toast.makeText(requireContext(), "Please register first", Toast.LENGTH_SHORT).show()
        }else{
            for(i in listAllUser.indices){
                if(listAllUser[i].email == email && listAllUser[i].password == password){
                    isFound = true
                    editor.putString("username", listAllUser[i].username)
                    editor.putBoolean("isLogin", true)
                    editor.apply()
                    navigateToHome()
                }
            }
            if(!isFound){
                Toast.makeText(requireContext(), "Email or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun navigateToHome(){
        findNavController().navigate(R.id.action_login_to_home)
    }
}