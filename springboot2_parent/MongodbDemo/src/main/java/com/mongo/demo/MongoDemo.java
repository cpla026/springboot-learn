package com.mongo.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * MongoDb入门小demo
 */
public class MongoDemo {
    public static void main(String[] args) {
        // 创建连接
        MongoClient client=new MongoClient("192.168.25.130",27017);
        // 指定数据库
        MongoDatabase coolron = client.getDatabase("coolron");
        // 指定集合(表)
        MongoCollection<Document> user = coolron.getCollection("user");
        // 查询所有记录
        FindIterable<Document> documents = user.find();   //查询记录获取文档集合
        for(Document document:documents){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("_id：" + document.getString("_id"));
            System.out.println("name:" + document.getString("name"));
            System.out.println("age：" + document.getInteger("age"));
            System.out.println("count：" + document.getDouble("count"));
        }
        // 关闭连接
        client.close();
    }
}
