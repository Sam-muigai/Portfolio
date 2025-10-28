package com.samkt.projects.di

import com.samkt.projects.ProjectsScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val projectScreenModule = module {
  viewModelOf(::ProjectsScreenViewModel)
}
