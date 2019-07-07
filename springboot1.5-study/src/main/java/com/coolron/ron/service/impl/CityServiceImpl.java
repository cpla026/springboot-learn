package com.coolron.ron.service.impl;

import com.coolron.ron.dao.CityMapper;
import com.coolron.ron.dao.UserMapper;
import com.coolron.ron.domain.City;
import com.coolron.ron.domain.User;
import com.coolron.ron.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private UserMapper userMapper;

    // RedisTemplate 封装了 RedisConnection，具有连接管理，序列化和 Redis 操作等功能
    // 解决客户端 key value 乱码请看 MyWebAppConfigurer 中配置
    @Autowired
    private RedisTemplate redisTemplate;

    public City findCityByName(String name) {
        return cityMapper.findByName(name);
    }

    /**
     * 获取城市逻
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     *
     * Redis 操作视图接口类用的是 ValueOperations，对应的是 Redis String/Value 操作。
     * 还有其他的操作视图，ListOperations、SetOperations、ZSetOperations 和 HashOperations 。
     * ValueOperations 插入缓存是可以设置失效时间，这里设置的失效时间是 20 s。
     */
    public City findCityById(Integer id) {
        // 从缓存中获取城市信息
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }

        // 从 DB 中获取城市信息
        City city = cityMapper.selectByPrimaryKey(id);

        // 插入缓存 key value 有效期 单位
        operations.set(key, city, 20, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());

        return city;
    }

    @Override
    public int  saveCity(City city) {
        return cityMapper.insertSelective(city);
    }

    /**
     * 更新城市逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    public int updateCity(City city) {
        int ret = cityMapper.updateByPrimaryKey(city);

        // 缓存存在，删除缓存
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
        }

        return ret;
    }

    @Override
    public int deleteCity(Integer id) {

        int ret = cityMapper.deleteByPrimaryKey(id);

        // 缓存存在，删除缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }
        return ret;
    }

    @Override
    public List<City> getCityList() {
        return cityMapper.findAllCity();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public  int addTrans(){
            City city = new City();
            city.setId(2222);
            city.setName("的法尔");
            city.setProvinceId(5l);
            city.setDescription("dgafadgafadsfdsafsdafas");
            cityMapper.insertSelective(city);

            int a = 1/0;

            User user = new User();
            user.setId(66);
            user.setName("llll");
            user.setAge(69);
            userMapper.insertSelective(user);
            return 0;

    }

    @Override
    public List<Map<String, Object>> getAll() {
        return userMapper.getAll();
    }
}
