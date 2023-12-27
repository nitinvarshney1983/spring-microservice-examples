package com.techwhisky.book.description.configuration;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "mongodb")
public class MongoDBProperties {

    private String host;
    private Integer port;
    private String uri;

    private String database;
    private String authenticationDatabase;

    private String userName;
    private String password;
    private boolean tlsEnabled;

    private String readPreference;
    private String writeConcern;
    private String readConcern;
    private Integer serverSelectionTimeout;
    private Integer connectionTimeout;
    private Integer readTimeout;
}
