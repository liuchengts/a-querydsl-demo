package com.ayouran.query.common.bo

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

class AddDeviceBO {

    @NotBlank(message = "设备名称不能为空")
    @ApiModelProperty(value = "设备名称", dataType = "string", required = true)
    var deviceName: String? = null

    @NotBlank(message = "设备名称不能为空")
    @ApiModelProperty(value = "脚本根路径", dataType = "string", required = true)
    var shellRoot: String? = null

    @NotBlank(message = "root密码不能为空")
    @ApiModelProperty(value = "root密码", dataType = "string", required = true)
    var rootPwd: String? = null

    @NotBlank(message = "远程ssh端口不能为空")
    @ApiModelProperty(value = "远程ssh端口", dataType = "long", required = true)
    var sshProt: Long? = null

    @ApiModelProperty(value = "备注", dataType = "string")
    var remarks: String? = null
}