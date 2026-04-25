package com.udes.profilerlab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductItem>>()
    val products: LiveData<List<ProductItem>> = _products

    private val baseList = (1..500).map {
        ProductItem(it, "Producto $it", it * 1.5, (0..100).random())
    }

    // Simula actualizaciones de stock cada 500ms
    fun startUpdates() {
        viewModelScope.launch {
            while (true) {
                delay(500)
                val updated = baseList.toMutableList()
                updated[0] = updated[0].copy(stock = (0..100).random())
                _products.value = updated
            }
        }
    }
}