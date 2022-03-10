package com.yzy.dao;

import com.yzy.pojo.Passenger;

import java.util.List;

public interface PassengerDao {
    boolean addPassenger(Passenger passenger);

    List<Passenger> findTravelAndPassenger(String tid);
}
