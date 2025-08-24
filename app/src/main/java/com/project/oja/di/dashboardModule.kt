package com.project.oja.di

import com.project.oja.data.repository.TimeLoggedRepositoryImpl
import com.project.oja.domain.repository.TimeLoggedRepository
import com.project.oja.domain.service.TimeLoggedService
import com.project.oja.presentation.components.calendar.CalendarViewModel
import com.project.oja.presentation.ui.dashboard.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module{

    single { TimeLoggedService() }


    single<TimeLoggedRepository> { TimeLoggedRepositoryImpl(get()) }


    viewModel { DashboardViewModel(get()) }
    viewModel { CalendarViewModel() }
}