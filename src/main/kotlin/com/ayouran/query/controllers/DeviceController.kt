package com.ayouran.query.controllers

import com.ayouran.query.common.RolesConstant
import com.ayouran.query.common.request.APageRequest
import com.ayouran.query.domain.service.DeviceService
import com.boot.auth.starter.annotation.Auth
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Auth(roles = [RolesConstant.FLOW_ADMIN])
@RestController
@RequestMapping("/device")
@Api(value = "device", description = "设备相关接口")
class DeviceController @Autowired
constructor(private val deviceService: DeviceService) {

    @ApiOperation(value = "查询设备列表")
    @GetMapping("/query_device")
    fun queryDevice(aPageRequest: APageRequest) = deviceService.queryDevice(aPageRequest)

}



