package com.apirest.backend.models


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
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

        @field:Email(message = "Format of Email is incorrect")
        @Column(unique=true, nullable = false)
        @get: Size(min = 1, max = 50)
        var email: String? = "",

        var img: String? = "",

        var createAt: OffsetDateTime = OffsetDateTime.now(),

        @JsonIgnoreProperties(value = arrayOf("hibernateLazyInitializer", "handler"))
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "region_id")
        var region: Region = Region(),

        @JsonIgnoreProperties(value = arrayOf("client", "hibernateLazyInitializer", "handler"), allowSetters = true)
        @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "client",
                cascade = arrayOf(CascadeType.ALL))
        var bills: List<Bill> = emptyList()
)