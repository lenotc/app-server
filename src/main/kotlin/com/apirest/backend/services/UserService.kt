package com.apirest.backend.services

import com.apirest.backend.models.UserClient
import com.apirest.backend.repository.IUserDao
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService : UserDetailsService, IUserService
{
    @Autowired
    lateinit var userRepository: IUserDao

    private var logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    @Throws(UsernameNotFoundException::class)
    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails
    {
        val userClient: UserClient = userRepository.findByUsername(username) ?:
            throw UsernameNotFoundException("Error on login: there are not user::: $username")


        val authorities: List<GrantedAuthority> = userClient.roles
                .map { SimpleGrantedAuthority(it.name) }

        return User(userClient.username,
                userClient.password,
                userClient.enabled,
                true,
                true,
                true,
                authorities)
    }

    override fun findByUsername(username: String): UserClient = userRepository.findByUsername(username)
}