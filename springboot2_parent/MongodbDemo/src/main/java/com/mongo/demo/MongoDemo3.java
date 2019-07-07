package com.mongo.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xf
 * @Date: 2018/12/7 13:39
 * @Description:
 */
public class MongoDemo3 {

    public static void main(String[] args) {
        // 创建连接
        MongoClient client=new MongoClient("192.168.25.130",27017);
        // 指定数据库
        MongoDatabase coolron = client.getDatabase("coolron");
        // 指定集合(表)
        MongoCollection<Document> user = coolron.getCollection("user");

        Map<String,Object> map=new HashMap();
        map.put("_id","6");
        map.put("name","rose");
        map.put("age",56);
        map.put("count",80);
        Document document=new Document(map);
        //插入数据
        user.insertOne(document);
        client.close();
    }

}
