package com.ayouran.query.domain.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.util.*
import javax.persistence.*

/****
 * 设备
 */
@Entity
@Table(name = "device",
        indexes = [
            Index(name = "device_name", columnList = "device_name")
        ])
@DynamicUpdate
@DynamicInsert
data class Device(@Id
                  @Column(name = "id", columnDefinition = "bigint(20) COMMENT 'ID,自增'")
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  var id: Long? = null,

                  @Column(name = "device_no", columnDefinition = "varchar(18) COMMENT '设备编号'", unique = true)
                  var deviceNo: String? = null,

                  @Column(name = "device_name", columnDefinition = "varchar(50) COMMENT '设备名称'", unique = true)
                  var deviceName: String? = null,

                  @Column(name = "shell_root", columnDefinition = "longtext COMMENT '脚本根路径'")
                  var shellRoot: String? = null,

                  @Column(name = "root_pwd", columnDefinition = "varchar(50) COMMENT 'root密码'")
                  var rootPwd: String? = null,

                  @Column(name = "ssh_prot", columnDefinition = "bigint(20) COMMENT 'ssh端口'")
                  var sshProt: Long? = null,

                  @Column(name = "remarks", columnDefinition = "longtext COMMENT '备注'")
                  var remarks: String? = null,

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
