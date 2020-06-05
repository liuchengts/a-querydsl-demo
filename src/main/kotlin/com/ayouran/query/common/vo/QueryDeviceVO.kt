package com.ayouran.query.common.vo

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.util.*

class QueryDeviceVO {
    @ApiModelProperty(value = "设备编号", dataType = "string")
    var deviceNo: String? = null

    @ApiModelProperty(value = "设备名称", dataType = "string")
    var deviceName: String? = null

    @ApiModelProperty(value = "脚本根路径", dataType = "string")
    var shellRoot: String? = null

    @ApiModelProperty(value = "root密码", dataType = "string")
    var rootPwd: String? = null

    @ApiModelProperty(value = "远程ssh端口", dataType = "long")
    var sshProt: Long? = null

    @ApiModelProperty(value = "备注", dataType = "string")
    var remarks: String? = null

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", dataType = "time")
    var createAt: Date? = null

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间", dataType = "time")
    var updateAt: Date? = null
}
