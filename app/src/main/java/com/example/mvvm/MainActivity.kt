package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewMode: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener(this)
        viewMode = ViewModelProvider(this).get(MainViewModel::class.java)

        setObserver()

    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_login){
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()


            viewMode.doLogin(email,password)
        }
    }

    private fun setObserver(){
        viewMode.welcome().observe(this) {
            binding.textWelcome.text = it
        }

        viewMode.login().observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "Falha na matrix!", Toast.LENGTH_LONG).show()
            }
        })
    }


}