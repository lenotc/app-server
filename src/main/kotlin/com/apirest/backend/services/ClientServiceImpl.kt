package com.apirest.backend.services

import com.apirest.backend.models.Client
import com.apirest.backend.models.Region
import com.apirest.backend.repository.IClientDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClientServiceImpl: ClientService
{
    @Autowired
    lateinit var repository: IClientDao

    override fun findAll(): List<Client> = repository.findAll().map { it }

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<Client>
    {
        return repository.findAll(pageable)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Client?
    {
        return repository.findById(id).orElse(null)
    }

    @Transactional
    override fun save(client: Client): Client
    {
        return repository.save(client)
    }

    @Transactional
    override fun delete(id: Long)
    {
        repository.deleteById(id)
    }

    @Transactional(readOnly = true)
    override fun findAllRegion(): List<Region>
    {
        return repository.findAllRegion();
    }
}