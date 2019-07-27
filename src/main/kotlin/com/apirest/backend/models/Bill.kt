package com.apirest.backend.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "bills")
data class Bill
(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        var description: String? = "",

        var observation: String? = "",

        @Column(name = "create_at")
        @CreationTimestamp
        var createAt: OffsetDateTime = OffsetDateTime.now(),

        @JsonIgnoreProperties(value = arrayOf("bills", "hibernateLazyInitializer", "handler"), allowSetters = true)
        @ManyToOne(fetch = FetchType.LAZY)
        var client: Client?,

        @JsonIgnoreProperties(value = arrayOf("hibernateLazyInitializer", "handler"))
        @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "bill_id")
        var items: List<BillItem> = emptyList()
)
{
        fun getTotal(): Double
        {
                var total = .0

                items.forEach{ total += it.getQuantity() }

                return total
        }
}