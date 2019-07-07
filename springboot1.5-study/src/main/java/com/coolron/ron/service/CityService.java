package com.coolron.ron.service;

import com.coolron.ron.domain.City;

import java.util.List;
import java.util.Map;

/**
 * 城市业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface CityService {

    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);

    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    City findCityById(Integer id);

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    int saveCity(City city);

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    int updateCity(City city);

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     * @return
     */
    int deleteCity(Integer id);

    List<City> getCityList();

    int addTrans();

    List<Map<String,Object>> getAll();
}
