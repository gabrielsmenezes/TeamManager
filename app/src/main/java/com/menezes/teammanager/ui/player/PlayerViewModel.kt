package com.menezes.teammanager.ui.player

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menezes.teammanager.R
import com.menezes.teammanager.repository.PlayerRepository
import kotlinx.coroutines.launch
import java.util.*

class PlayerViewModel(private val repository: PlayerRepository) : ViewModel() {

    private val _playerStateEventData = MutableLiveData<PlayerState>()
    val playerStateEventData: LiveData<PlayerState> get() = _playerStateEventData


    private val _messageStateEventData = MutableLiveData<Int>()
    val messageStateEventData: LiveData<Int> get() = _messageStateEventData

    fun addPlayer(name: String, purchasePrice: Float, purchaseDate: String, sellPrice: Float, sellDate: String) = viewModelScope.launch {
        try {
            val id = repository.insertPlayer(name, purchasePrice, purchaseDate, sellPrice, sellDate)
            if (id > 0) {
                _playerStateEventData.value = PlayerState.Inserted
                _messageStateEventData.value = R.string.player_inserted_successfully
            }
        } catch (ex: Exception) {
            _messageStateEventData.value = R.string.player_error_to_insert
            Log.e(TAG, ex.toString())
        }
    }


    sealed class PlayerState {
        object Inserted : PlayerState()
    }

    companion object {
        private val TAG = PlayerViewModel::class.java.simpleName
    }
}