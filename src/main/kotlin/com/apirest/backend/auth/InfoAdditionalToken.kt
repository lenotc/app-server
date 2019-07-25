package com.apirest.backend.auth

import com.apirest.backend.services.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.stereotype.Component

@Component
class InfoAdditionalToken : TokenEnhancer
{
    @Autowired
    lateinit var userService: IUserService

    @Throws(Exception::class)
    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken
    {
        val userClient = userService.findByUsername(authentication.name)

        val info = mapOf<String, Any>("name" to "${userClient.id} :: ${userClient.name}",
                "alias" to "${userClient.id} :: ${userClient.alias}",
                "email" to "${userClient.id} :: ${userClient.email}")

        (accessToken as DefaultOAuth2AccessToken).additionalInformation = info

        return accessToken
    }
}