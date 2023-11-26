package com.kai.composeanimelist.di

import com.kai.composeanimelist.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}