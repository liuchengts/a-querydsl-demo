package com.ayouran.query.common.request

import io.swagger.annotations.ApiModelProperty

open class APageRequest {
    private val DEFAULT_PAGE_SIZE: Long = 20L
    /**
     * 防止拖库
     */
    private val MAX_PAGE_SIZE: Long = 100L
    @ApiModelProperty(value = "搜索词", dataType = "string")
    var query: String? = null

    @ApiModelProperty(value = "排序", dataType = "string")
    var sort: String? = null

    @ApiModelProperty(value = "页码，即第几页，从1开始", dataType = "long")
    var pageIndex: Long? = 1L

    @ApiModelProperty(value = "每页记录数，默认20，最大100", dataType = "long")
    var pageSize: Long? = 20L

    fun getPageIndex(): Long {
        // 前端传的是以1开始的，后端需要以0开始的
        return if (pageIndex == null || pageIndex!! <= 1) 0 else pageIndex!! - 1
    }

    fun getPageSize(): Long {
        if (pageSize == null || pageSize!! < 1) pageSize = DEFAULT_PAGE_SIZE
        else if (pageSize!! > MAX_PAGE_SIZE) pageSize = MAX_PAGE_SIZE
        return pageSize!!
    }

    fun getSortDefault(): String {
        return "create_at desc"
    }
}