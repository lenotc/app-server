package com.apirest.backend.services

import com.apirest.backend.models.Bill
import com.apirest.backend.models.Client
import com.apirest.backend.models.Product
import com.apirest.backend.models.Region
import com.apirest.backend.repository.IBillDao
import com.apirest.backend.repository.IClientDao
import com.apirest.backend.repository.IProductDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClientServiceImpl: ClientService
{
    @Autowired
    lateinit var clientRepository: IClientDao

    @Autowired
    lateinit var billRepository: IBillDao

    @Autowired
    lateinit var productRepository: IProductDao

    override fun findAll(): List<Client> = clientRepository.findAll().map { it }

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<Client>
    {
        return clientRepository.findAll(pageable)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Client?
    {
        return clientRepository.findById(id).orElse(null)
    }

    @Transactional
    override fun save(client: Client): Client
    {
        return clientRepository.save(client)
    }

    @Transactional
    override fun delete(id: Long)
    {
        clientRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    override fun findAllRegion(): List<Region>
    {
        return clientRepository.findAllRegion();
    }

    @Transactional(readOnly = true)
    override fun findBillById(id: Long): Bill
    {
        return billRepository.findById(id).orElse(null)
    }

    @Transactional
    override fun saveBill(bill: Bill): Bill
    {
        return billRepository.save(bill)
    }

    @Transactional
    override fun deleteBillById(id: Long)
    {
        billRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    override fun findProductByName(name: String): List<Product>
    {
        return productRepository.findByName(name)
    }
}