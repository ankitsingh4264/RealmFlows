package com.example.realmflows.Repository

import android.util.Log
import com.example.realmflows.Entity.RealmFeedBack
import com.example.realmflows.Entity.UiState
import com.example.realmflows.Utils.Constants
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow
import java.lang.Exception

class Repository {

    val config= RealmConfiguration.Builder()
    .schemaVersion(Constants.realmVersion)
    .build()


    suspend fun insertFeed(realmFeedBack: RealmFeedBack) : Flow<UiState>{
        val realm = Realm.getInstance(config)
        return flow<UiState> {
            try {
                realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
                    realmTransaction.insert(realmFeedBack)
                }
                emit(UiState.SUCCESS)
            }catch (ex:Exception){
                emit(UiState.FAIL)
            }

        }


    }
    suspend fun getFeed(){
        val realm = Realm.getInstance(config)

        try {
            realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
               val temp :List<RealmFeedBack> = realmTransaction.where(RealmFeedBack::class.java).findAll()

                Log.d("ankit", "getFeed: ${temp}")
            }
        }catch (ex:Exception){

        }
    }


}