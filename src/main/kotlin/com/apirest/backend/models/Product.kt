package com.apirest.backend.models

import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "products")
data class Product
(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        var name: String = "",

        var price: Double = .0,

        @CreationTimestamp
        @Column(name = "create_at")
        var createAt: OffsetDateTime = OffsetDateTime.now()
)