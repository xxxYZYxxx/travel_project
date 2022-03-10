package com.yzy.service.impl;

import com.yzy.dao.TravelDao;
import com.yzy.dao.impl.TravelDaoImpl;
import com.yzy.pojo.Travel;
import com.yzy.service.TravelService;

import java.util.List;

public class TravelServiceImpl implements TravelService {

    private TravelDao travelDao=new TravelDaoImpl();
    @Override
    public List<Travel> getTravelList() {
        return travelDao.getTravelList();
    }

    @Override
    public Travel findTravelById(String tid) {
        return travelDao.findTravelById(tid);
    }

    @Override
    public boolean toUpdateById(Travel travel) {
        return travelDao.toUpdateById(travel);
    }

    @Override
    public int deleteTravelByIds(String ids) {
        //查询每个id对应路线的报名人数
        List<Travel> travels=travelDao.findNumById(ids);
        for (Travel travel : travels) {
            if(travel.getNum()>0){
                return 2;
            }
        }
        //flag为1时进行删除
        return travelDao.deleteTravelByIds(ids);

    }
}
