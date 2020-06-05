package com.ayouran.query.domain.service.impl

import com.ayouran.query.common.bo.LoginBO
import com.ayouran.query.common.vo.UserVO
import com.ayouran.query.domain.repository.*
import com.ayouran.query.domain.service.UserService
import com.boot.auth.starter.common.AuthConstant
import com.boot.auth.starter.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class UserServiceImpl @Autowired
constructor(private val userRepository: UserRepository,
            private val authService: AuthService) : UserService {
    private val logger = LoggerFactory.getLogger(javaClass)
    override fun login(loginBO: LoginBO,
                       request: HttpServletRequest,
                       response: HttpServletResponse): UserVO {
        val vo = UserVO()
        val option = userRepository.findByName(loginBO.name!!)
        if (option.isPresent) {
            if (option.get().pwd == loginBO.pwd) {
                vo.nick = option.get().name
                val parameters = mutableMapOf<String, Any>()
                parameters[AuthConstant.SESSION_NICK_NAME] = vo.nick!!
                vo.xtoken = authService.auth("", option.get().userNo, option.get().roles, parameters, response, request)
                vo.code = "200"
                vo.successful = true
            } else vo.code = "-200"
        } else vo.code = "-300"
        return vo
    }

}