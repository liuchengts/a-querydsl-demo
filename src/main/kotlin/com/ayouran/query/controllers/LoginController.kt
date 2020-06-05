package com.ayouran.query.controllers

import com.ayouran.query.common.bo.LoginBO
import com.ayouran.query.domain.service.UserService
import com.boot.auth.starter.service.AuthService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/login")
@Api(value = "login", description = "登录相关接口")
class LoginController @Autowired
constructor(private val authService: AuthService,
            private val userService: UserService) {

    @ApiOperation(value = "检查token是否有效(true表示有效)")
    @GetMapping("/check_token")
    fun checkToken(request: HttpServletRequest) = authService.checkToken(request)

    @ApiOperation(value = "登录")
    @PostMapping("/")
    fun login(@Valid loginBO: LoginBO,
              request: HttpServletRequest,
              response: HttpServletResponse) = userService.login(loginBO, request, response)
}



