package com.apirest.backend.repository

import com.apirest.backend.models.Client
import com.apirest.backend.models.Region
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IClientDao: JpaRepository<Client, Long>
{
    @Query("from Region")
    fun findAllRegion(): List<Region>
}