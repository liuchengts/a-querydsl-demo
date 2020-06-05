package com.ayouran.query.domain.mapstruct

import com.ayouran.query.common.bo.AddDeviceBO
import com.ayouran.query.common.vo.QueryDeviceVO
import com.ayouran.query.domain.model.Device
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface DeviceMapstruct {
    fun toQueryDeviceVO(device: Device): QueryDeviceVO
    fun toDevice(addDeviceBO: AddDeviceBO): Device

}