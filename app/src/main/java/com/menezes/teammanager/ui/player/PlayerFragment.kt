package com.menezes.teammanager.ui.player

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.menezes.teammanager.R
import com.menezes.teammanager.data.db.AppDatabase
import com.menezes.teammanager.extension.hideKeyboard
import com.menezes.teammanager.repository.DatabaseDataSource
import kotlinx.android.synthetic.main.player_fragment.*
import java.text.SimpleDateFormat
import androidx.fragment.app.viewModels
import com.menezes.teammanager.repository.PlayerRepository


class PlayerFragment : Fragment(R.layout.player_fragment) {

    private val viewModel: PlayerViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val playerDao = AppDatabase.getInstance(requireContext()).playerDAO
                val repository: PlayerRepository = DatabaseDataSource(playerDao)
                return PlayerViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.playerStateEventData.observe(viewLifecycleOwner) { playerState ->
            when (playerState) {
                is PlayerViewModel.PlayerState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()

                    findNavController().popBackStack()
                }
            }
        }

        viewModel.messageStateEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_SHORT).show()

        }
    }

    private fun clearFields() {
        input_name.text?.clear()
        input_purchase_price.text?.clear()
        input_purchase_date.text?.clear()
        input_sell_price.text?.clear()
        input_sell_date.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_add_player.setOnClickListener {
            val name = input_name.text.toString()
            val purchasePrice = input_purchase_price.text.toString().toFloat()
            val purchaseDate = input_purchase_date.text.toString()
            val sellPrice = input_sell_price.text.toString().toFloat()
            val sellDate = input_sell_date.text.toString()

            viewModel.addPlayer(name, purchasePrice, purchaseDate, sellPrice, sellDate)
        }
    }


}