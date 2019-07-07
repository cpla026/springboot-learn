package com.mongo.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @Auther: xf
 * @Date: 2018/12/7 09:28
 * @Description:
 * 查询浏览量大于1000的记录
 */
public class MongoDemo2 {
    public static void main(String[] args) {
        // 创建连接
        MongoClient client=new MongoClient("192.168.25.130",27017);
        // 指定数据库
        MongoDatabase coolron = client.getDatabase("coolron");
        // 指定集合(表)
        MongoCollection<Document> user = coolron.getCollection("user");

        // 构建查询条件
        BasicDBObject bson=new BasicDBObject("age",new BasicDBObject("$gt",18) );

        FindIterable<Document> documents = user.find(bson);//查询记录获取结果集合
        for(Document document:documents){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println("_id：" + document.getString("_id"));
            System.out.println("name:" + document.getString("name"));
            System.out.println("age：" + document.getInteger("age"));
            System.out.println("count：" + document.getDouble("count"));
        }
        client.close();//关闭连接
    }
}
