package com.littleapp.poke.data

import com.littleapp.poke.data.network.ApiService
import com.littleapp.poke.domain.model.PokeItem
import com.littleapp.poke.domain.model.PokeItemDetails
import com.littleapp.poke.domain.model.toDomain
import javax.inject.Inject

class PokeRepository @Inject constructor(private val api: ApiService) {

    suspend fun getAllPokemons(): List<PokeItem> {
        val response = api.getPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun getPokeDetails(id: Int): PokeItemDetails? {
        val response = api.getDetailsPokemon(id)
        return response?.toDomain()
    }
}
