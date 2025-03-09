package com.test.teststore.app.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results
import com.test.teststore.domain.usecases.GetDetailsOfProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getDetailsOfProductUseCase: GetDetailsOfProductUseCase
) : ViewModel() {

    private val _actionProduct = MutableSharedFlow<Product>(replay = 1)
    val actionProduct: SharedFlow<Product> = _actionProduct

    private val _actionMessage = MutableSharedFlow<String>(replay = 1)
    val actionMessage: SharedFlow<String> = _actionMessage

    fun getDetailsOfProduct(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        when (val results = getDetailsOfProductUseCase.execute(id = id)) {
            is Results.Success -> {
                _actionProduct.tryEmit(results.data)
            }

            is Results.Error -> {
                _actionMessage.tryEmit(results.exception.message.toString())
            }
        }
    }
}