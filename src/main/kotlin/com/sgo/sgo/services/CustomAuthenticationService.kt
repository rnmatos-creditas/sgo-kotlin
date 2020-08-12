package com.sgo.sgo.services

import com.sgo.sgo.auth.KeycloakAuthenticationResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

@Service
class CustomAuthenticationService {

    fun authenticateUser(username: String, password: String) : KeycloakAuthenticationResponse {
        val request = RestTemplate()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val clientId = System.getenv("keycloak-client-id")
        val clientSecret = System.getenv("keycloak-client-secret")
        val arguments = LinkedMultiValueMap<String, String>()
        arguments.add("grant_type", "password")
        arguments.add("client_id", clientId)
        arguments.add("client_secret", clientSecret)
        arguments.add("username", username)
        arguments.add("password", password)

        val req = HttpEntity<MultiValueMap<String, String>>(arguments, headers)

        val url = "http://localhost:8080/auth/realms/sgo/protocol/openid-connect/token"
        return request.postForObject(url,req,KeycloakAuthenticationResponse::class,arguments)
    }
}