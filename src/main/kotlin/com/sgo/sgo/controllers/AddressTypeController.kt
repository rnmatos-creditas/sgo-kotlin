package com.sgo.sgo.controllers

import com.sgo.sgo.entities.ClientOutputDTO
import com.sgo.sgo.entities.domains.AddressType
import com.sgo.sgo.services.AddressTypeService
import com.sgo.sgo.utils.decodeParam
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("/address-types")
@Tag(name = "Address type", description = "Manage address types")
class AddressTypeController {

    @Autowired
    lateinit var addressTypeService: AddressTypeService

    @GetMapping
    @RolesAllowed("admin")
    @Operation(summary = "List all address types")
    fun listAll() : ResponseEntity<List<AddressType>> {
        val addressTypes = addressTypeService.findAll()
        return ResponseEntity.ok().body(addressTypes)
    }
}