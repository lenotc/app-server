package com.apirest.backend.models

import javax.persistence.*

@Entity
@Table(name = "regions")
data class Region
(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var name: String = ""
)