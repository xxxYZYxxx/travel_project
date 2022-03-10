package com.yzy.service.impl;

import com.yzy.dao.PassengerDao;
import com.yzy.dao.TravelDao;
import com.yzy.dao.impl.PassengerDaoImpl;
import com.yzy.dao.impl.TravelDaoImpl;
import com.yzy.pojo.Passenger;
import com.yzy.service.PassengerService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PassengerServiceImpl implements PassengerService {
    private PassengerDao passengerDao=new PassengerDaoImpl();
    private TravelDao travelDao=new TravelDaoImpl();
    @Override
    public boolean addPassenger(Passenger passenger) {
        //先获取当前的时间
        Date date = new Date();
        //创建日期格式化对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");
        //将日期转换为字符串
        String format = simpleDateFormat.format(date);
        //将当前系统时间赋值给实体地报名时间
        passenger.setSignDate(format);
            boolean flag=passengerDao.addPassenger(passenger);
            if(flag==true){
                //路线的报名人数需+1
                return travelDao.getTravelByOne(passenger.getTid());
        }
        return false;
    }

    @Override
    public List<Passenger> findTravelAndPassenger(String tid) {
        return passengerDao.findTravelAndPassenger(tid);
    }
}
