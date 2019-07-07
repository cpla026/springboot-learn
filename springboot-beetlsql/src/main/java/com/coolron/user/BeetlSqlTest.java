package com.coolron.user;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

/**
 * @Auther: xf
 * @Date: 2018/12/2 20:31
 * @Description: 测试 BeetlSQL 内置API
 */
public class BeetlSqlTest {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/springbootdb?useSSL=false&useUnicode=true&characterEncoding=utf8";
        String userName = "root";
        String password = "root";

        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new  UnderlinedNameConversion();

        /**
         * 创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
         * SQLManager 是系统的核心，他提供了所有的dao方法。获得SQLManager，可以直接构造SQLManager
         */
        SQLManager sqlManager = new SQLManager(mysql,loader,source,nc,new Interceptor[]{new DebugInterceptor()});
         /*
         sqlManager 内置api 用于生产 pojo 和 SQL
         */
         /*
        genPojoCodeToConsole(String table), 根据表名生成pojo类，输出到控制台.
        genPojoCode(String table,String pkg,String srcPath,GenConfig config) 根据表名，包名，生成路径，还有配置，生成pojo代码
        genPojoCode(String table,String pkg,GenConfig config) 同上，生成路径自动是项目src路径，或者src/main/java (如果是maven工程)
        genPojoCode(String table,String pkg),同上，采用默认的生成配置

         genSQLTemplateToConsole(String table),生成查询，条件，更新sql模板，输出到控制台。
        genSQLFile(String table), 同上，但输出到工程，成为一个sql模版,sql模版文件的位置在src目录下，或者src／main／resources（如果是maven）工程.
        genALL(String pkg,GenConfig config,GenFilter filter) 生成所有的pojo代码和sql模版，
        genBuiltInSqlToConsole(Class z) 根据类来生成内置的增删改查sql语句，并打印到控制台
        */
        try {
            //sql.genPojoCodeToConsole("userRole"); 快速生成，显示到控制台
            // 或者直接生成java文件
            // config 类用来配置生成喜好,目前支持生成pojo是否继承某个基类, 是否用BigDecimal代替Double,是否采用Date而不是Timestamp来表示日期，是否是直接输出到控制台而不是文件等
            //GenConfig config = new GenConfig();
            //config.preferBigDecimal(true);
            // 是否继承某个类
            //config.setBaseClass("com.coolron.test.User");
            //sqlManager.genPojoCode("user","com.coolron.test",config);

            // 生产 实体类
            //sqlManager.genPojoCodeToConsole("user");
            // 生成 SQL
            sqlManager.genSQLTemplateToConsole("user");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
