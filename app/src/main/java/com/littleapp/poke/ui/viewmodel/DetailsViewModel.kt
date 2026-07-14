package com.littleapp.poke.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littleapp.poke.domain.GetDetails
import com.littleapp.poke.domain.model.PokeItemDetails
import com.littleapp.poke.ui.view.DetailFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

enum class ApiStatusDetail { LOADING, ERROR, DONE }

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getDetails: GetDetails
) : ViewModel() {

    private var _pokeDetails = MutableLiveData<PokeItemDetails>()
    val pokeDetails: LiveData<PokeItemDetails> get() = _pokeDetails

    private var _status = MutableLiveData<ApiStatusDetail>()
    val status: LiveData<ApiStatusDetail>
        get() = _status

    init {
        getPokemonDetails(DetailFragment.idP)
    }

    private fun getPokemonDetails(id: Int) {
        _status.value = ApiStatusDetail.LOADING
        viewModelScope.launch {
            try {
                _pokeDetails.value = getDetails.fromPokemon(id)
                _status.value = ApiStatusDetail.DONE
            } catch (e: Exception) {
                _status.value = ApiStatusDetail.ERROR
                Timber.d(e.message)
            }
        }
    }
}
