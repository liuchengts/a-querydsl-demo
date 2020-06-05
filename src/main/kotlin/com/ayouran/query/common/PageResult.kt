package com.ayouran.query.common


class PageResult<T> {
    var pageInfo: PageInfo? = null
    var result: Collection<T>? = null

    class PageInfo {
        /**
         * 是否是最后一页
         */
        var isLastPage: Boolean? = null
        /**
         * 页码，从1开始
         */
        var pageIndex: Long? = null
        /**
         * 每页记录数
         */
        var pageSize: Long? = null
        /**
         * 总记录数
         */
        var total: Long? = null
        /**
         * 总页数
         */
        var pageTotal: Long? = null
    }
}