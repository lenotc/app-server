package com.apirest.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class ApirestApplication: CommandLineRunner
{
    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    override fun run(vararg args: String?)
    {
        for (i in 1..4)
        {
            println(passwordEncoder.encode("12345"))
        }
    }
}

fun main(args: Array<String>)
{
    runApplication<ApirestApplication>(*args)
}
