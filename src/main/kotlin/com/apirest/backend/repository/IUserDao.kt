package com.apirest.backend.repository

import com.apirest.backend.models.UserClient
import org.springframework.data.repository.CrudRepository

interface IUserDao: CrudRepository<UserClient, Long>
{
    fun findByUsername(username: String): UserClient
}