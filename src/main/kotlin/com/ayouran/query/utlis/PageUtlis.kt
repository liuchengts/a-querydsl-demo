package com.ayouran.query.utlis

import com.ayouran.query.common.PageResult
import com.querydsl.core.QueryResults
import java.util.function.Function
import java.util.stream.Collectors

object PageUtlis {
    /**
     * 分页 返回 PageResult
     * val results = querydslUtlis.getQueryFactory()
     * .select(*exprs)
     * .from(qDevice)
     * .where(ExpressionUtils.allOf(rredicate))
     * .orderBy(qDevice.createAt.desc())
     * .offset((aPageRequest.pageIndex!! - 1) * aPageRequest.pageSize!!)
     * .limit(aPageRequest.pageSize!!)
     * .fetchResults()
     *  return PageUtlis.retPage(results, querydslUtlis.getCollection(results.results, exprs, QueryDeviceVO::class.java) as Collection<QueryDeviceVO>)
     */
    fun <T, R> retPage(queryResults: QueryResults<T>, list: Collection<R>): PageResult<R> {
        val page = PageResult<R>()
        page.result = list
        val pageInfo = PageResult.PageInfo()
        pageInfo.pageIndex = queryResults.offset
        pageInfo.pageSize = queryResults.limit
        pageInfo.total = queryResults.total
        pageInfo.pageTotal = queryResults.results.size.toLong()
        pageInfo.isLastPage = pageInfo.pageIndex!! * pageInfo.pageSize!! - pageInfo.total!! >= 0
        page.pageInfo = pageInfo
        return page
    }

    /**
     * 分页 返回 PageResult
     * 例如
     * querydslUtlis.getQueryFactory()
     *  .select(*exprs)
     *  .from(qDeviceRules)
     *  .where(ExpressionUtils.allOf(rredicate))
     *  .leftJoin(qFlowRules)
     *  .on(qDeviceRules.rulesNo.eq(qFlowRules.rulesNo))
     *  .orderBy(qDeviceRules.createAt.desc())
     *  .offset((aPageRequest.pageIndex!! - 1) * aPageRequest.pageSize!!)
     *  .limit(aPageRequest.pageSize!!)
     *  .fetchResults(), Function<Tuple, QueryDeviceFlowRulesVO> {
     *    querydslUtlis.getBean(it, exprs, QueryDeviceFlowRulesVO::class.java) as QueryDeviceFlowRulesVO
     * }
     */
    fun <T, R> retPage(queryResults: QueryResults<T>, function: Function<T, R>): PageResult<R> {
        val page = PageResult<R>()
        page.result = toList(queryResults.results, function)
        val pageInfo = PageResult.PageInfo()
        pageInfo.pageIndex = queryResults.offset
        pageInfo.pageSize = queryResults.limit
        pageInfo.total = queryResults.total
        pageInfo.pageTotal = queryResults.results.size.toLong()
        pageInfo.isLastPage = pageInfo.pageIndex!! * pageInfo.pageSize!! - pageInfo.total!! >= 0
        page.pageInfo = pageInfo
        return page
    }

    private fun <T, R> toList(col: Collection<T>, function: Function<T, R>): List<R>? {
        return col.stream().map(function).collect(Collectors.toList<R>())
    }

    /**
     * 分页 返回 PageResult
     */
    fun <T> retPage(data: Collection<T>, pageNumber: Long?, pageSize: Long?, totalCount: Long?): PageResult<T> {
        val page = PageResult<T>()
        val pageInfo = PageResult.PageInfo()
        pageInfo.pageIndex = pageNumber
        pageInfo.pageSize = pageSize
        pageInfo.total = totalCount
        pageInfo.pageTotal = data.size.toLong()
        pageInfo.isLastPage = pageInfo.pageIndex!! * pageInfo.pageSize!! - pageInfo.total!! >= 0
        page.result = data
        page.pageInfo = pageInfo
        return page
    }

}