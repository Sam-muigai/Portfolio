package com.samkt.contact.di

import com.samkt.contact.ContactScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val contactScreenModule = module {
    viewModelOf(::ContactScreenViewModel)
}