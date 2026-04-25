package com.udes.profilerlab

import androidx.recyclerview.widget.DiffUtil

class ProductDiffCallback : DiffUtil.ItemCallback<ProductItem>() {

    // Compara identidad: ¿es el mismo item en la lista?
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem.id == newItem.id
    }

    // Compara contenido: ¿cambió algún dato visible?
    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem == newItem // data class compara todos los campos
    }
}