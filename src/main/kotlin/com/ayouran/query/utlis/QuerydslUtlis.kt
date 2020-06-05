package com.ayouran.query.utlis

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.querydsl.core.Tuple
import com.querydsl.core.types.Expression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import javax.annotation.PostConstruct
import javax.persistence.EntityManager

/**
 * 对querydsl的查询结果做了一个简化解析的实现，具体输出参考 PageUtlis
 * @see com.ayouran.flow.utlis.PageUtlis
 */
@Component
class QuerydslUtlis @Autowired
constructor(private val entityManager: EntityManager) {
    private val logger = LoggerFactory.getLogger(javaClass)
    //查询工厂实体
    private var queryFactory: JPAQueryFactory? = null
    //jackson对象
    private var objectMapper: ObjectMapper? = null

    @PostConstruct
    private fun initFactory() {
        logger.info("开始实例化JPAQueryFactory")
        queryFactory = JPAQueryFactory(entityManager)
    }

    @PostConstruct
    private fun objectMapperConfig() {
        if (objectMapper == null) objectMapper = ObjectMapper()
        objectMapper!!.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        objectMapper!!.dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 获得查询工厂实例
     *
     * @return JPAQueryFactory
     */
    fun getQueryFactory(): JPAQueryFactory {
        if (queryFactory == null) initFactory()
        return queryFactory!!
    }

    /**
     * 将com.querydsl.core.Tuple转换为需要的bean
     *
     * @param tuple 查询得到的数据
     * @param exprs 查询的列
     * @param clazz 要转换的目标 class类型
     * @return 返回根据 clazz转换的 class -> Object对象
     * ==== 注意 =======
     * @see objectMapperConfig
     * 需要在有 objectMapperConfig 的配置前提
     * @see com.fasterxml.jackson.annotation.JsonProperty
     * 默认取最后的字段名字与clazz的类字段名称匹配，如有不匹配的字段 请自行使用@JsonProperty注解来匹配
     * 匹配规则为 exprs 查询参数的全称
     *
     * 示例：
     * querydslUtlis.getBean(it, exprs, QueryDeviceFlowRulesVO::class.java) as QueryDeviceFlowRulesVO
     */
    fun <T> getBean(tuple: Tuple, exprs: Array<Expression<*>>, clazz: Class<T>): Any {
        val map = getBeanMap(tuple, exprs)
        return if (map.isEmpty()) Any() else mapToBean(map, clazz)
    }

    /**
     * 将内容转换为mapbean
     *
     * @param tuple 查询得到的数据
     * @param exprs 查询的列
     * @return 返回一个基于bean属性的 map
     */
    private fun getBeanMap(tuple: Tuple, exprs: Array<Expression<*>>): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        exprs.forEach {
            val value = getValue(tuple, it)
            val key = it.toString()
            val index = key.lastIndexOf(".")
            var subKey = ""
            if (index != -1) subKey = key.substring(key.lastIndexOf(".") + 1)
            if (subKey.isNotEmpty() && !map.containsKey(subKey)) {
                map[subKey] = value
            } else {
                map[key] = value
            }
        }
        return map
    }

    /**
     * 获取值
     * @param tuple 查询的结果对象
     * @param expression 查询的字段
     * @return 值
     */
    private fun getValue(tuple: Tuple, expression: Expression<*>): Any {
        var value = tuple[expression]
        value = when {
            value != null -> {
                value
            }
            (expression.type.name == "java.lang.Long"
                    || expression.type.name == "java.lang.Int") -> {
                0
            }
            (expression.type.name == "java.lang.Number"
                    || expression.type.name == "java.lang.Double"
                    || expression.type.name == "java.lang.Float") -> {
                0.00
            }
            else -> {
                expression.type.newInstance()
            }
        }
        return value
    }

    /**
     * 将List<com.querydsl.core.Tuple>转换为需要的List<bean>
     *
     * @param tuples 查询得到的数据结果集
     * @param exprs 查询的列
     * @param clazz 要转换的目标 class类型
     * @return 返回根据 clazz转换的 class -> List<Object>对象
     * ==== 注意 =======
     * @see objectMapperConfig
     * 需要在有 objectMapperConfig 的配置前提
     * @see com.fasterxml.jackson.annotation.JsonProperty
     * 默认取最后的字段名字与clazz的类字段名称匹配，如有不匹配的字段 请自行使用@JsonProperty注解来匹配
     * 匹配规则为 exprs 查询参数的全称
     * 示例：
     * querydslUtlis.getList(results.results, exprs, QueryDeviceVO::class.java) as List<QueryDeviceVO>
     */
    fun <T> getList(tuples: MutableList<Tuple>, exprs: Array<Expression<*>>, clazz: Class<T>): MutableList<T> {
        val list = mutableListOf<Map<String, Any>>()
        tuples.forEach { tuple ->
            val map = getBeanMap(tuple, exprs)
            logger.info("${map["head"]}")
            if (map.isNotEmpty()) list.add(map)
        }
        return listToListBean(list, getCollectionType(List::class.java, clazz))
    }

    // 将对象转成字符串
    @Throws(Exception::class)
    private fun objectToString(obj: Any): String {
        return objectMapper!!.writeValueAsString(obj)
    }

    // 将Map转成指定的Bean
    @Throws(Exception::class)
    private fun <T> mapToBean(map: Map<*, *>, clazz: Class<T>): Any {
        return objectMapper!!.readValue(objectToString(map), clazz)!!
    }

    // 将Bean转成Map
    @Throws(Exception::class)
    private fun beanToMap(obj: Any): Map<*, *> {
        return objectMapper!!.readValue(objectToString(obj), MutableMap::class.java)
    }

    // 将list<map>转成指定的list<Bean>
    @Throws(Exception::class)
    private fun <T, R> listToListBean(list: MutableList<T>, clazz: JavaType): MutableList<R> {
        return objectMapper!!.readValue(objectToString(list), clazz)
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    private fun getCollectionType(collectionClass: Class<*>, vararg elementClasses: Class<*>): JavaType {
        return objectMapper!!.typeFactory.constructParametricType(collectionClass, *elementClasses)
    }
}