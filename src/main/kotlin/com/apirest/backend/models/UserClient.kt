package com.apirest.backend.models

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class UserClient
(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(unique = true, nullable = false)
        @get:Size(min = 1, max = 20)
        var username: String = "",

        @Column(length = 60)
        var password: String = "",

        var enabled: Boolean = false,

        var name: String = "",

        var alias: String = "",

        @Email
        @Column(unique = true, nullable = false)
        @get:Size(min = 1, max = 50)
        var email: String = "",

        @ManyToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
        @JoinTable(name = "users_authorities",
                joinColumns = arrayOf(JoinColumn(name = "user_id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "role_id")),
                uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("user_id", "role_id"))))
        var roles: List<Role>
)