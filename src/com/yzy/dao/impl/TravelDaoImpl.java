package com.yzy.dao.impl;

import com.yzy.dao.TravelDao;
import com.yzy.pojo.Travel;
import com.yzy.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TravelDaoImpl implements TravelDao {

    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    //获取路线列表
    @Override
    public List<Travel> getTravelList() {
        try {
            ArrayList<Travel> travels = new ArrayList<>();
            connection=JDBCUtil.getConnection();
            //获取数据库操作对象
            String sql="select * from tb_travel";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            //遍历结果集
            while(resultSet!=null&&resultSet.next()){
                int id = resultSet.getInt("id");
                String travelName = resultSet.getString("travel_name");
                String travleDesc = resultSet.getString("travel_desc");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                String tripStart = resultSet.getString("trip_start");
                String tripEnd = resultSet.getString("trip_end");
                double price = resultSet.getDouble("price");
                int num = resultSet.getInt("num");
                Travel travel = new Travel(id, travelName, travleDesc, startDate, endDate, tripStart, tripEnd, price, num);
                travels.add(travel);
            }
            return travels;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.JDBCClose(connection,preparedStatement,resultSet);
        }
        return null;
    }

    @Override
    public boolean getTravelByOne(Integer tid) {
        try {
            connection= JDBCUtil.getConnection();
            //获取数据库操作对象
            String sql="update tb_travel set num=num+1 where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,tid);
            int num = preparedStatement.executeUpdate();
            if(num==1){
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.JDBCClose(connection,preparedStatement,resultSet);
        }
        return false;
    }

    @Override
    public Travel findTravelById(String tid) {
        try {
            Travel travel = new Travel();
            connection=JDBCUtil.getConnection();
            //获取数据库操作对象
            String sql="select * from tb_travel where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(tid));
            resultSet=preparedStatement.executeQuery();
            //遍历结果集
            if(resultSet!=null&&resultSet.next()){
                int id = resultSet.getInt("id");
                String travelName = resultSet.getString("travel_name");
                String travleDesc = resultSet.getString("travel_desc");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                String tripStart = resultSet.getString("trip_start");
                String tripEnd = resultSet.getString("trip_end");
                double price = resultSet.getDouble("price");
                int num = resultSet.getInt("num");
                travel = new Travel(id, travelName, travleDesc, startDate, endDate, tripStart, tripEnd, price, num);
            }
            return travel;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.JDBCClose(connection,preparedStatement,resultSet);
        }
        return null;
    }

    //根据id修改线路数据
    @Override
    public boolean toUpdateById(Travel travel) {
        try {
            connection= JDBCUtil.getConnection();
            //获取数据库操作对象
            String sql="update tb_travel set travel_name=?,travel_desc=?,start_date=?,end_date=?,trip_start=?,trip_end=?,price=? where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,travel.getTravelName());
            preparedStatement.setString(2,travel.getTravelDesc());
            preparedStatement.setString(3,travel.getStartDate());
            preparedStatement.setString(4,travel.getEndDate());
            preparedStatement.setString(5,travel.getTripStart());
            preparedStatement.setString(6,travel.getTripEnd());
            preparedStatement.setDouble(7,travel.getPrice());
            preparedStatement.setInt(8,travel.getTid());
            int num = preparedStatement.executeUpdate();
            if(num==1){
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.JDBCClose(connection,preparedStatement,resultSet);
        }
        return false;
    }

    @Override
    public int deleteTravelByIds(String ids) {
        try {
            connection= JDBCUtil.getConnection();
            //获取数据库操作对象
            String sql="delete from tb_travel where id in("+ids+")";
            preparedStatement=connection.prepareStatement(sql);
            int num = preparedStatement.executeUpdate();
            if(num>0){
                return 1;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.JDBCClose(connection,preparedStatement,resultSet);
        }
        return 3;
    }

    //判断线路是否有人报名
    @Override
    public List<Travel> findNumById(String ids) {
        try {
            ArrayList<Travel> travels = new ArrayList<>();
            connection=JDBCUtil.getConnection();
            //获取数据库操作对象
            String sql="select * from tb_travel where id in("+ids+")";

            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            //遍历结果集
            while(resultSet!=null&&resultSet.next()){
                int num = resultSet.getInt("num");
                Travel travel = new Travel();
                travel.setNum(num);
                travels.add(travel);
            }
            return travels;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.JDBCClose(connection,preparedStatement,resultSet);
        }
        return null;
    }

}
