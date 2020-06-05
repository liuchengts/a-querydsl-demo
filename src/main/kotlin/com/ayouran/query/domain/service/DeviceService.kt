package com.ayouran.query.domain.service

import com.ayouran.query.common.PageResult
import com.ayouran.query.common.request.APageRequest
import com.ayouran.query.common.vo.QueryDeviceVO

/**
 * 设备相关
 */
interface DeviceService {
    /**
     * 查询设备信息
     */
    fun queryDevice(aPageRequest: APageRequest): PageResult<QueryDeviceVO>

}