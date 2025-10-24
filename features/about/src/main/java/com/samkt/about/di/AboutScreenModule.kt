package com.samkt.about.di

import com.samkt.about.AboutScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val aboutScreenModule = module {
    viewModelOf(::AboutScreenViewModel)
}