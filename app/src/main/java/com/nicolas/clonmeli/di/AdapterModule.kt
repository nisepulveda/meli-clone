package com.nicolas.clonmeli.di

import com.nicolas.clonmeli.ui.product.adapter.ProductAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesProductAdapter(): ProductAdapter {
        return ProductAdapter()
    }
}