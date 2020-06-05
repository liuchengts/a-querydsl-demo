package com.ayouran.query.domain.repository

import com.ayouran.query.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long>,
        JpaSpecificationExecutor<User>, QuerydslPredicateExecutor<User> {
    fun findByName(name: String): Optional<User>
}