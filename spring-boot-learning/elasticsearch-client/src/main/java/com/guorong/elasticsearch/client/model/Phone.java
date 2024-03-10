package com.guorong.elasticsearch.client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "phone-02")
public class Phone implements Serializable {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String brandName;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private Date birthday;

    @Field(type = FieldType.Integer)
    private Integer level;
}
