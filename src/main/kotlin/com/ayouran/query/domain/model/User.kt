package com.ayouran.query.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.util.*
import javax.persistence.*

/****
 * 用户
 */
@Entity
@Table(name = "user")
@DynamicUpdate
@DynamicInsert
data class User(@Id
                @Column(name = "id", columnDefinition = "bigint(20) COMMENT 'ID,自增'")
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long? = null,

                @Column(name = "user_no", columnDefinition = "varchar(18) COMMENT '用户编号'", unique = true)
                var userNo: String? = null,

                @Column(name = "name", columnDefinition = "varchar(20) COMMENT '用户名'", unique = true)
                var name: String? = null,

                @Column(name = "pwd", columnDefinition = "varchar(50) COMMENT '密码'")
                var pwd: String? = null,

                @Column(name = "roles", columnDefinition = "longtext COMMENT '权限'")
                var roles: String? = null,

                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
                @Column(name = "create_at", columnDefinition = "datetime COMMENT '创建时间'")
                var createAt: Date? = null,

                @Column(name = "update_at", columnDefinition = "datetime COMMENT '修改时间'")
                var updateAt: Date? = null) {


    /**
     * 触发jpa update代码需要执行的逻辑
     */
    @PreUpdate
    fun preUpdate() {
        updateAt = Date()
    }

    /**
     * 自动设置必要字段的值
     */
    @PrePersist
    fun prePersist() {
        updateAt = Date()
        createAt = updateAt
    }
}
