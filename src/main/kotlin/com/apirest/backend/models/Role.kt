package com.apirest.backend.models


import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "roles")
data class Role
(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(unique = true, length = 20)
        @get:Size(min = 1, max = 20)
        var name: String = ""
)
