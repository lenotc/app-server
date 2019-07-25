package com.apirest.backend.services

import com.apirest.backend.models.UserClient

interface IUserService
{
    fun findByUsername(username: String): UserClient
}