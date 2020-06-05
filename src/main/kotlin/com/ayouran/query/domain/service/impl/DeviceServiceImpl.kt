package com.ayouran.query.domain.service.impl

import com.ayouran.query.common.PageResult
import com.ayouran.query.common.request.APageRequest
import com.ayouran.query.common.vo.QueryDeviceVO
import com.ayouran.query.domain.model.QDevice
import com.ayouran.query.domain.service.DeviceService
import com.ayouran.query.utlis.PageUtlis
import com.ayouran.query.utlis.QuerydslUtlis
import com.querydsl.core.types.Expression
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Predicate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class DeviceServiceImpl @Autowired
constructor(private val querydslUtlis: QuerydslUtlis) : DeviceService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun queryDevice(aPageRequest: APageRequest): PageResult<QueryDeviceVO> {
        val qDevice = QDevice.device
        var rredicate: Predicate? = null
        if (StringUtils.hasText(aPageRequest.query)) rredicate = qDevice.deviceName.contains(aPageRequest.query)
                .or(qDevice.deviceNo.contains(aPageRequest.query))
        val exprs = arrayOf<Expression<*>>(qDevice.deviceNo, qDevice.deviceName, qDevice.shellRoot, qDevice.rootPwd, qDevice.sshProt,
                qDevice.remarks, qDevice.createAt, qDevice.updateAt)
        val results = querydslUtlis.getQueryFactory()
                .select(*exprs)
                .from(qDevice)
                .where(ExpressionUtils.allOf(rredicate))
                .orderBy(qDevice.createAt.desc())
                .offset((aPageRequest.getPageIndex()) * aPageRequest.getPageSize())
                .limit(aPageRequest.getPageSize())
                .fetchResults()
        return PageUtlis.retPage(results, querydslUtlis.getList(results.results, exprs,
                QueryDeviceVO::class.java))
    }
}