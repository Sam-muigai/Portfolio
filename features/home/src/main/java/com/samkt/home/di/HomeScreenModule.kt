package com.samkt.home.di

import com.samkt.home.HomeScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeScreenModule = module {
  viewModelOf(::HomeScreenViewModel)
}
