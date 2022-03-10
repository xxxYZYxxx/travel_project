package com.yzy.service;

import com.yzy.pojo.Travel;

import java.util.List;

public interface TravelService {
    List<Travel> getTravelList();

    Travel findTravelById(String tid);

    boolean toUpdateById(Travel travel);

    int deleteTravelByIds(String ids);
}
