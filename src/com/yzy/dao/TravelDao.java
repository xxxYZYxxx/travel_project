package com.yzy.dao;

import com.yzy.pojo.Travel;

import java.util.List;

public interface TravelDao {
    List<Travel> getTravelList();

    boolean getTravelByOne(Integer tid);

    Travel findTravelById(String tid);

    boolean toUpdateById(Travel travel);

    int deleteTravelByIds(String ids);

    List<Travel> findNumById(String ids);
}
