package com.yzy.service;

import com.yzy.pojo.Passenger;

import java.util.List;

public interface PassengerService {
    boolean addPassenger(Passenger passenger);

    List<Passenger> findTravelAndPassenger(String tid);
}
