package com.littleapp.poke.data.network

import com.littleapp.poke.data.model.PokeModel
import com.littleapp.poke.data.model.PokeModelDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getPokemons(): List<PokeModel> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getListPokemon()
            response.body()?.pokemons ?: emptyList()
        }
    }

    suspend fun getDetailsPokemon(id: Int): PokeModelDetails? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getDetailsPokemon(id)
            response.body()
        }
    }
}
