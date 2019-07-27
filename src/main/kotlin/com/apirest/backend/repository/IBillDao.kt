package com.apirest.backend.repository

import com.apirest.backend.models.Bill
import org.springframework.data.repository.CrudRepository

interface IBillDao: CrudRepository<Bill, Long>
{
}