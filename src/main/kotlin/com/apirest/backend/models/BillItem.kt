package com.apirest.backend.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "bill_items")
data class BillItem
(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        var amount: Int = 0,

        @JsonIgnoreProperties(value = arrayOf("hibernateLazyInitializer", "handler"))
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id")
        var product: Product = Product()
)
{
    fun getQuantity(): Double = amount * product.price
}