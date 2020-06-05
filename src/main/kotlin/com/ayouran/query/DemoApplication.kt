package com.ayouran.query

import com.boot.auth.starter.common.Session
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@EntityScan
@ComponentScan("com.ayouran")
@EnableJpaRepositories
@SpringBootApplication
open class DemoApplication : CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, *args)
        }
    }

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Session::class.java)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.ayouran.querydsl.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(ApiInfoBuilder()
                        .description("ayouram-flow相关API")
                        .title("ayouram-flow")
                        .version("1.0")
                        .contact(Contact("lc","",""))
                        .build())
                .pathMapping("/")
    }

    override fun run(vararg args: String?) {
        logger.info("*************************** ok ***********************************")
    }
}