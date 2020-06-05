package com.ayouran.query.common.vo

import io.swagger.annotations.ApiModelProperty

class UserVO {
    @ApiModelProperty(value = "状态码", dataType = "int")
    var code: String? = null

    @ApiModelProperty(value = "用户编号", dataType = "string")
    var userNo: String? = null

    @ApiModelProperty(value = "手机号", dataType = "string")
    var mobile: String? = null

    @ApiModelProperty(value = "是新用户(是:true,否:false)", dataType = "boolean")
    var newUser: Boolean = false

    @ApiModelProperty(value = "是已禁用用户(是:true,否:false)", dataType = "boolean")
    var deletedUser: Boolean = false

    @ApiModelProperty(value = "用户昵称", dataType = "string")
    var nick: String? = null

    @ApiModelProperty(value = "用户头像", dataType = "string")
    var photo: String? = null

    @ApiModelProperty(value = "X-TOKEN", dataType = "string")
    var xtoken: String? = null

    @ApiModelProperty(value = "是否登录成功", dataType = "boolean")
    var successful: Boolean = false
}
