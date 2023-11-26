package com.kai.composeanimelist.data

import com.kai.composeanimelist.model.AnimeData
import com.kai.composeanimelist.model.AnimeDummy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {

    private val animeList = mutableListOf<AnimeDummy>()

    init {
        if (animeList.isEmpty()) {
            AnimeData.animes.forEach {
                animeList.add(AnimeDummy(it))
            }
        }
    }

    fun getAnime(): Flow<List<AnimeDummy>> {
        return flowOf(animeList)
    }

    fun getAnimeById(animeId: Long): AnimeDummy {
        return animeList.first {
            it.anime.id == animeId
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}