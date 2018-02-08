package com.bitclave.node.repository.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Account(
        @Column(length = 256, unique = true) @Id val publicKey: String = ""
) {
    @JsonIgnore
    fun isValid(): Boolean = publicKey.length == 66
}
