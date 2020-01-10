package com.shawn.community.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

// db name, table name
@Data
@Document(indexName = "community_article",type = "article")
public class Article implements Serializable {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String title;
    private String content;
    private String state;
}
