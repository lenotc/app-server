package com.apirest.backend.repository

import com.apirest.backend.models.Product
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface IProductDao: CrudRepository<Product, Long>
{
    @Query("SELECT p FROM Product p WHERE p.name like %?1%")
    fun findByName(name: String): List<Product>

    fun findByNameContainingIgnoreCase(name: String): List<Product>

    fun findByNameStartingWithIgnoreCase(name: String): List<Product>
}