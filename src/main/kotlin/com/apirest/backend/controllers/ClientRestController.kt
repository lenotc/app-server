package com.apirest.backend.controllers

import com.apirest.backend.models.Client
import com.apirest.backend.models.Region
import com.apirest.backend.services.ClientService
import com.apirest.backend.services.UploadFile
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.dao.DataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import javax.validation.Valid

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api")
class ClientRestController
{
    @Autowired
    lateinit var service: ClientService

    @Autowired
    lateinit var upload: UploadFile

    private val log: Logger = LoggerFactory.getLogger(ClientRestController::class.java)

    @GetMapping("clients")
    fun index(): List<Client>
    {
        return service.findAll()
    }

    @GetMapping("/clients/page/{page}")
    fun page(@PathVariable page: Int): Page<Client>
    {
        return service.findAll(PageRequest.of(page, 4))
    }

    @Secured("ROLE_ADMIN", "ROLE_USER")
    @GetMapping("clients/{id}")
    fun show(@PathVariable id: Long): ResponseEntity<Any>
    {
        try
        {

            val client = service.findById(id)
            if (client == null)
            {
                val error = mapOf<String, String>("message" to "The Client ID $id not exist")
                return ResponseEntity(error, HttpStatus.NOT_FOUND)
            }

            val success = mapOf<String, Client>("client" to client)

            return ResponseEntity(success, HttpStatus.OK)
        } catch (e: DataAccessException)
        {
            val error = mapOf<String, String>("message" to "Error on access data in database",
                    "error" to "${e.message} ${e.mostSpecificCause.message}")
            return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @Secured("ROLE_ADMIN")
    @PostMapping("clients")
    fun create(@Valid @RequestBody client: Client, result: BindingResult): ResponseEntity<Any>
    {
        if (result.hasErrors())
        {
            val invalid: List<String> = result.fieldErrors.map { "The Field : ${it.field} --- ${it.defaultMessage}" }
            val errors = mapOf<String, List<String>>("errors" to invalid)
            return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
        }

        try
        {
            println(client)
            val clientSaved = service.save(client)

            val success = mapOf<String, Any>("client" to clientSaved, "message" to "Client has been created")

            return ResponseEntity(success, HttpStatus.CREATED)
        } catch (e: DataAccessException)
        {
            val error = mapOf<String, String>("message" to "Error on save data in database",
                    "error" to "${e.message} ${e.mostSpecificCause.message}")
            return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("clients/{id}")
    fun updated(@Valid @RequestBody client: Client, result: BindingResult, @PathVariable id: Long): ResponseEntity<Any>
    {
        if (result.hasErrors())
        {
            val invalid: List<String> = result.fieldErrors.map { "The Field : ${it.field} --- ${it.defaultMessage}" }
            val errors = mapOf<String, List<String>>("errors" to invalid)
            return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
        }

        try
        {
            val clientChanged = service.findById(id)

            if (clientChanged != null)
            {
                clientChanged.name = client.name
                clientChanged.alias = client.alias
                clientChanged.email = client.email
                clientChanged.region = client.region

                service.save(clientChanged)

                val success = mapOf<String, Any>("client" to clientChanged, "message" to "client has bee updated success")

                return ResponseEntity(success, HttpStatus.CREATED)
            } else
            {
                val error = mapOf<String, String>("message" to "The client has not been found")
                return ResponseEntity(error, HttpStatus.NOT_FOUND)
            }

        } catch (e: DataAccessException)
        {
            val error = mapOf<String, String>("message" to "Error on updated data in database",
                    "error" to "${e.message} ${e.mostSpecificCause.message}")
            return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("clients/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any>
    {
        try
        {
            val client = service.findById(id)
            val imgBefore: String? = client!!.img

            upload.delete(imgBefore)

            service.delete(id)

            val success = mapOf<String, String>("message" to "Client delete success")

            return ResponseEntity(success, HttpStatus.OK)
        } catch (e: DataAccessException)
        {
            val error = mapOf<String, String>("message" to "Error on delete data in database",
                    "error" to "${e.message} ${e.mostSpecificCause.message}")
            return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Secured("ROLE_ADMIN", "ROLE_USER")
    @PostMapping("clients/upload")
    fun upload(@RequestParam("file") file: MultipartFile, @RequestParam("id") id: Long): ResponseEntity<Any>
    {
        val client = service.findById(id)

        if (!file.isEmpty && client != null)
        {
            val fileName: String;

            try
            {
                fileName = upload.copy(file)
            } catch (e: IOException)
            {
                val error = mapOf<String, String>("message" to "Error on save the image of the client",
                        "error" to "${e.message} :: ${e.cause!!.message}")

                return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
            }

            val imgBefore: String? = client.img
            upload.delete(imgBefore)


            client.img = fileName
            service.save(client)

            val success = mapOf<String, Any>("client" to client, "message" to "Image save success ${fileName}")
            return ResponseEntity(success, HttpStatus.CREATED)
        } else
        {
            val error = mapOf<String, String>("message" to "Error on save the image")
            return ResponseEntity(error, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/uploads/img/{imgName:.+}")
    fun getImg(@PathVariable imgName: String): ResponseEntity<Resource>
    {
        val resource: Resource = upload.upload(imgName)

        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${resource.filename}\"")

        return ResponseEntity(resource, headers, HttpStatus.OK)
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/clients/regions")
    fun listRegion(): List<Region>
    {
        return service.findAllRegion()
    }
}