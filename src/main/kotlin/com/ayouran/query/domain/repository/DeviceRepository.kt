package com.ayouran.query.domain.repository

import com.ayouran.query.domain.model.Device
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : JpaRepository<Device, Long>,
        JpaSpecificationExecutor<Device>, QuerydslPredicateExecutor<Device>