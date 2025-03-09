package com.test.teststore.app.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results
import com.test.teststore.domain.usecases.GetAllProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _actionProducts = MutableSharedFlow<List<Product>>(replay = 1)
    val actionProduct: SharedFlow<List<Product>> = _actionProducts

    private val _actionMessage = MutableSharedFlow<String>(replay = 1)
    val actionMessage: SharedFlow<String> = _actionMessage

    fun getAllProducts() = viewModelScope.launch(Dispatchers.IO) {
        when (val results = getAllProductsUseCase.execute()) {
            is Results.Success -> {
                _actionProducts.tryEmit(results.data)
            }

            is Results.Error -> {
                _actionMessage.tryEmit(results.exception.message.toString())
            }
        }

    }
}