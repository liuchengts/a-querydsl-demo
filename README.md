a-querydsl-demo
---------------------------

### kotlin集成springboot、querydsl、mapstruct等示例

## 注意
* 在使用 ``` QuerydslUtlis ``` 工具来序列化查询数据时，需要注意反序列化的数据格式

例如: 
```
/**
     * 这里使用了json的反序列化来填充，需要加上@JsonDeserialize
     */
    @JsonDeserialize(using = LocalDatetimeDeserializer.class)
    LocalDateTime goTimeEnd;
```