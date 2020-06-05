package com.ayouran.query.common

import com.boot.auth.starter.common.DefaultRolesConstant

/**
 * 角色枚举
 */
interface RolesConstant : DefaultRolesConstant {
    companion object {
        /**
         * admin
         */
        const val FLOW_ADMIN = "admin"
        /**
         * user
         */
        const val FLOW_USER = "user"
    }
}