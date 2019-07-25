package com.apirest.backend.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig: AuthorizationServerConfigurerAdapter()
{
    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    @Autowired
    @Qualifier("authenticationManager")
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var infoAdditionalToken: InfoAdditionalToken

    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer)
    {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer)
    {
        clients.inMemory()
                .withClient("angularapp")
                .secret(passwordEncoder.encode("12345"))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600)
    }

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer)
    {
        val tokenEnhancerChain = TokenEnhancerChain()
        tokenEnhancerChain.setTokenEnhancers(listOf(infoAdditionalToken, accessTokenConverter()))

        endpoints.authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(tokenEnhancerChain)
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter
    {
        val jwtAccessTokenConverter = JwtAccessTokenConverter()
        jwtAccessTokenConverter.setSigningKey(RSA_PRIVATE)
        jwtAccessTokenConverter.setVerifierKey(RSA_PUBLIC)
        return jwtAccessTokenConverter
    }
}