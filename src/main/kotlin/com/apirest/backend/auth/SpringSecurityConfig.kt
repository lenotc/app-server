package com.apirest.backend.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
class SpringSecurityConfig : WebSecurityConfigurerAdapter()
{
    @Autowired
    lateinit var userService: UserDetailsService

    @Autowired
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder)
    {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity)
    {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager = super.authenticationManager()

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

}
