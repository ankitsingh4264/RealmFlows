package com.example.realmflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.realmflows.Entity.RealmFeedBack
import com.example.realmflows.databinding.ActivityMainBinding
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
      viewModel.getFeed()
        binding.btnSave.setOnClickListener {
            val data=RealmFeedBack(name = binding.edtName.text.toString() ,message=  binding.edtFeedback.text.toString())
            viewModel.insertData(data)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.insertDataState.collect {
                Log.d("ankit", "onCreate: $it ")
            }
        }




    }

}