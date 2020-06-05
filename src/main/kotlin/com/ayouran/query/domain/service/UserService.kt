package com.ayouran.query.domain.service

import com.ayouran.query.common.bo.LoginBO
import com.ayouran.query.common.vo.UserVO
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 用户 业务
 */
interface UserService {

    /**
     * 登录
     */
    fun login(loginBO: LoginBO,
              request: HttpServletRequest,
              response: HttpServletResponse): UserVO
}