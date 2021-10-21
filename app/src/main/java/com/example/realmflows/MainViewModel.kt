package com.example.realmflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realmflows.Entity.RealmFeedBack
import com.example.realmflows.Entity.UiState
import com.example.realmflows.Repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel :ViewModel() {
    @ExperimentalCoroutinesApi
    private  val _insertDataState= MutableStateFlow<UiState>(UiState.EMPTY)
    @ExperimentalCoroutinesApi
    val insertDataState=_insertDataState


    private val repo=Repository()

    @ExperimentalCoroutinesApi
    fun insertData(data:RealmFeedBack){

         viewModelScope.launch {
            repo.insertFeed(data).collect {
                _insertDataState.value=it
            }
         }

    }
    fun getFeed(){
        viewModelScope.launch {
            repo.getFeed()
        }
    }
}