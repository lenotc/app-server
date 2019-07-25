package com.apirest.backend.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.validator.constraints.UniqueElements
import java.time.LocalDate
import java.time.OffsetDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity
@Table(name = "`clients`")
@EntityListeners
data class Client
(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @field:NotEmpty
        @field:Size(min = 2)
        var name: String = "",

        var alias: String = "",

        @field:Email(message = "Formatte of Email is incorrect")
        @Column(unique=true, nullable = false)
        @get: Size(min = 1, max = 50)
        var email: String? = "",

        var img: String? = "",

        var createAt: OffsetDateTime = OffsetDateTime.now(),

//        @JsonIgnoreProperties(value = arrayOf("hibernateLazyInitializer", "handler"))
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "region_id")
        var region: Region = Region()
)