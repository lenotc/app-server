package com.apirest.backend.controllers

import com.apirest.backend.models.Bill
import com.apirest.backend.models.Product
import com.apirest.backend.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("http://localhost:4200"))
@RestController
@RequestMapping("/api")
class BillRestController
{
    @Autowired
    lateinit var clientService: ClientService

    @GetMapping("/bills/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    fun show(@PathVariable id: Long): Bill
    {
        return clientService.findBillById(id)
    }

    @DeleteMapping("/bills/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long): Unit
    {
        clientService.deleteBillById(id)
    }

    @GetMapping("/bills/products/{name}")
    @ResponseStatus(HttpStatus.OK)
    fun findProduct(@PathVariable name: String): List<Product>
    {
        return clientService.findProductByName(name)
    }

    @PostMapping("/bills")
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody bill: Bill): Bill
    {
        return clientService.saveBill(bill)
    }
}