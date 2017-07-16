package com.zhangtao.config.dbconfig;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao on 2017/7/16.
 */
@Configuration
//@ConfigurationProperties(prefix = "mongodb.log2")
public class MongoDBConfiguration {

    @Value("${mongodb.log1.host}")
    private String hosts;
    @Value("${mongodb.log1.username}")
    private String username;
    @Value("${mongodb.log1.password}")
    private String password;
    @Value("${mongodb.log1.database}")
    private String database;

    @Value("${mongodb.log2.host}")
    private String hosts2;
    @Value("${mongodb.log2.username}")
    private String username2;
    @Value("${mongodb.log2.password}")
    private String password2;
    @Value("${mongodb.log2.database}")
    private String database2;

    @Primary
    @Bean(name = "mongoLog1Template")
    public MongoTemplate mongoLog1Template() throws Exception {
        return new MongoTemplate(mongoLog1DbFactory(), getMongoConverter());
//        return new MongoTemplate(mongoDbFactory(), null);
    }

    @Bean(name = "mongoLog2Template")
    public MongoTemplate mongoLog2Template() throws Exception {
        return new MongoTemplate(mongoLog2DbFactory(), getMongoConverter());
//        return new MongoTemplate(mongoDbFactory(), null);
    }

    private MongoConverter getMongoConverter() throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoLog2DbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        MongoTypeMapper typeMapper = new DefaultMongoTypeMapper(null);
        converter.setTypeMapper(typeMapper);
        converter.afterPropertiesSet();
        return converter;
    }

    private ServerAddress parseServerAddress(String host)
            throws UnknownHostException {
        ServerAddress addr = null;
        int mh = host.indexOf(':');
        if (mh < 0) {
            addr = new ServerAddress(host);
        } else {
            String serv = host.substring(0, mh).trim();
            int port = Integer.parseInt(host.substring(mh + 1).trim());
            addr = new ServerAddress(serv, port);
        }
        return addr;
    }

    @Primary
    @Bean(name = "mongoLog1")
    public MongoClient mongo1() throws Exception {
        List<MongoCredential> authList = new ArrayList<>();
        // 【mongodb 3.x】
        MongoCredential auth = MongoCredential.createScramSha1Credential(
                username, database, password.toCharArray());
        // 【mongodb 2.x】
        //    MongoCredential auth = MongoCredential.createMongoCRCredential(
        //            username, database, password.toCharArray());
        String[] hostArray = hosts.split("[,]");
        int countMongoServers = hostArray.length;
        while (countMongoServers-- > 0) {
            authList.add(auth);
        }
        MongoClient mongo = null;
        if (hostArray.length == 1) {
            ServerAddress addr = parseServerAddress(hosts);
            mongo = new MongoClient(addr, authList);
        } else {
            List<ServerAddress> reps = new ArrayList<>();
            for (String host : hostArray) {
                ServerAddress addr = parseServerAddress(host);
                reps.add(addr);
            }
            mongo = new MongoClient(reps, authList);
        }
        return mongo;
    }

    @Bean(name = "mongoLog2")
    public MongoClient mongo2() throws Exception {
        List<MongoCredential> authList = new ArrayList<>();
        // 【mongodb 3.x】
        MongoCredential auth = MongoCredential.createScramSha1Credential(
                username2, database2, password2.toCharArray());
        // 【mongodb 2.x】
        //    MongoCredential auth = MongoCredential.createMongoCRCredential(
        //            username, database, password.toCharArray());
        String[] hostArray = hosts2.split("[,]");
        int countMongoServers = hostArray.length;
        while (countMongoServers-- > 0) {
            authList.add(auth);
        }
        MongoClient mongo = null;
        if (hostArray.length == 1) {
            ServerAddress addr = parseServerAddress(hosts2);
            mongo = new MongoClient(addr, authList);
        } else {
            List<ServerAddress> reps = new ArrayList<>();
            for (String host : hostArray) {
                ServerAddress addr = parseServerAddress(host);
                reps.add(addr);
            }
            mongo = new MongoClient(reps, authList);
        }
        return mongo;
    }

    private MongoDbFactory mongoLog1DbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongo1(), database);
    }

    private MongoDbFactory mongoLog2DbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongo2(), database2);
    }
}