package com.test.teststore.app.screens.local

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.teststore.domain.models.Product
import com.test.teststore.domain.usecases.GetLocalAllProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalProductsViewModel @Inject constructor(
    private val getLocalAllProductsUseCase: GetLocalAllProductsUseCase
) : ViewModel() {

    private val _actionProduct = MutableSharedFlow<List<Product>>(replay = 1)
    val actionProduct: SharedFlow<List<Product>> = _actionProduct

    private val _actionMessage = MutableSharedFlow<String>(replay = 1)
    val actionMessage: SharedFlow<String> = _actionMessage

    fun getLocalProducts() = viewModelScope.launch(Dispatchers.IO) {
        _actionProduct.tryEmit(getLocalAllProductsUseCase.execute())
    }
}