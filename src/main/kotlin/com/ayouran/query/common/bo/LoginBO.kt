package com.ayouran.query.common.bo

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

class LoginBO {
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号", dataType = "string", required = true)
    var name: String? = null

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", dataType = "string", required = true)
    var pwd: String? = null

    @NotBlank(message = "平台不能为空")
    @ApiModelProperty(value = "平台(pc:1)", dataType = "int", required = true)
    var platform: Int? = null
}