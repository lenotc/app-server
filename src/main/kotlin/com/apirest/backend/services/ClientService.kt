package com.apirest.backend.services

import com.apirest.backend.models.Bill
import com.apirest.backend.models.Client
import com.apirest.backend.models.Product
import com.apirest.backend.models.Region
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ClientService
{
    fun findAll(): List<Client>

    fun findAll(pageable: Pageable): Page<Client>

    fun findById(id: Long): Client?

    fun save(client: Client): Client

    fun delete(id: Long): Unit

    fun findAllRegion(): List<Region>

    fun findBillById(id: Long): Bill

    fun saveBill(bill: Bill): Bill;

    fun deleteBillById(id: Long): Unit

    fun findProductByName(name: String): List<Product>
}