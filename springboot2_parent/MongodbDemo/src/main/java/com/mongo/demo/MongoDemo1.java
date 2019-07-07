package com.mongo.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @Auther: xf
 * @Date: 2018/12/7 09:25
 * @Description:  条件查询
 * BasicDBObject对象：
 * 表示一个具体的记录，BasicDBObject实现了DBObject，是keyvalue 的数据结构，用起来和HashMap是基本一致的。
 */
public class MongoDemo1 {

    public static void main(String[] args) {
        // 创建连接
        MongoClient client=new MongoClient("192.168.25.130",27017);
        // 指定数据库
        MongoDatabase coolron = client.getDatabase("coolron");
        // 指定集合(表)
        MongoCollection<Document> user = coolron.getCollection("user");
        // 构建查询 条件
        BasicDBObject bson=new BasicDBObject("age",18);
        FindIterable<Document> documents = user.find(bson);
        for(Document document:documents){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("_id：" + document.getString("_id"));
            System.out.println("name:" + document.getString("name"));
            System.out.println("age：" + document.getInteger("age"));
            System.out.println("count：" + document.getDouble("count"));
        }
        //关闭连接
        client.close();
    }

}
