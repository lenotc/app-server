package com.apirest.backend.auth


import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableResourceServer
class ResourceServerConfig: ResourceServerConfigurerAdapter()
{
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity)
    {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/api/clients",
                        "/api/clients/page/**",
                        "/api/uploads/img/**",
                        "/images/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/clients/{id}").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/clients/upload").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/clients").hasRole("ADMIN")
//                .antMatchers("/api/clients/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().cors().configurationSource(corsConfigurationSource())
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource
    {
        val config = CorsConfiguration()
        config.allowedOrigins = arrayListOf("http://localhost:4200")
        config.allowedMethods = arrayListOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        config.allowCredentials = true
        config.allowedHeaders = arrayListOf("Content-Type", "Authorization")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter>
    {
        val bean = FilterRegistrationBean<CorsFilter>(CorsFilter(corsConfigurationSource()));
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean;
    }
}