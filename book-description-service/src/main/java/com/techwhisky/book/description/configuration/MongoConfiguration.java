package com.techwhisky.book.description.configuration;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.SocketSettings;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableMongoRepositories(basePackages = "com.techwhisky.book.description.persistence.repository")
public class MongoConfiguration {

    @Autowired
    private MongoDBProperties mongoDBProperties;


    @Bean
    public MongoTemplate mongoTemplate() {
        return buildMongoTemplate(mongoDBProperties);
    }

    private MongoTemplate buildMongoTemplate(MongoDBProperties mongoDBProperties) {
        MongoClient mongoClient = buildMongoClient(mongoDBProperties);
        return new MongoTemplate(mongoClient,mongoDBProperties.getDatabase());
    }

    private MongoClient buildMongoClient(MongoDBProperties mongoDBProperties) {
        MongoClientSettings.Builder builder = MongoClientSettings.builder();
        if (!StringUtils.isEmpty(mongoDBProperties.getUri())) {
            builder.applyConnectionString(new ConnectionString(mongoDBProperties.getUri()));
        } else {
            builder.applyToClusterSettings(builder1 ->
                    builder1.hosts(Collections.singletonList(new ServerAddress(mongoDBProperties.getHost(),
                            mongoDBProperties.getPort()))));
        }

        //tls enabling
        builder.applyToSslSettings(builder1 -> builder1.enabled(mongoDBProperties.isTlsEnabled()));
        //authentication
        if(mongoDBProperties.getAuthenticationDatabase()!=null){
            MongoCredential mongoCredential=MongoCredential.createCredential(mongoDBProperties.getUserName(),
                    mongoDBProperties.getAuthenticationDatabase(),
                    mongoDBProperties.getPassword().toCharArray());
            builder.credential(mongoCredential);
        }
        //socket settings
        SocketSettings socketSettings=SocketSettings.builder().connectTimeout(mongoDBProperties.getConnectionTimeout(),TimeUnit.MILLISECONDS)
                .readTimeout(mongoDBProperties.getReadTimeout(), TimeUnit.MILLISECONDS)
                .build();
        builder.applyToSocketSettings(builder1 -> builder1.applySettings(socketSettings));
        builder.readConcern(new ReadConcern(ReadConcernLevel.fromString(mongoDBProperties.getReadConcern())));
        builder.readPreference(ReadPreference.valueOf(mongoDBProperties.getReadPreference()));
        builder.writeConcern(WriteConcern.valueOf(mongoDBProperties.getWriteConcern()));

        MongoClientSettings mongoClientSettings=builder.build();
        return MongoClients.create(mongoClientSettings);
    }

}
