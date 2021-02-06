package com.livin.androidtest.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.livin.androidtest.data.repository.HomeItemsRepository

class HomeViewModel @ViewModelInject constructor(
    private val repository: HomeItemsRepository
) : ViewModel() {

    val homeItems = repository.getAllHomeItems()
}
