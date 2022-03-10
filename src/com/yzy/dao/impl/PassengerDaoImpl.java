package com.yzy.dao.impl;

import com.yzy.dao.PassengerDao;
import com.yzy.pojo.Passenger;
import com.yzy.pojo.Travel;
import com.yzy.utils.JDBCUtil;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDaoImpl implements PassengerDao {
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;

    @Override
    public boolean addPassenger(Passenger passenger) {
        try {
            connection= JDBCUtil.getConnection();
            //获取数据库操作对象
            String sql="insert into tb_passenger(name,sex,birthday,phone,sign_date,idCard,tid) values(?,?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,passenger.getName());
            preparedStatement.setInt(2,passenger.getSex());
            preparedStatement.setString(3,passenger.getBirthday());
            preparedStatement.setString(4,passenger.getPhone());
            preparedStatement.setString(5,passenger.getSignDate());
            preparedStatement.setString(6,passenger.getIdCard());
            preparedStatement.setInt(7,passenger.getTid());
            int num = preparedStatement.executeUpdate();
            if(num==1){
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Passenger> findTravelAndPassenger(String tid) {
        try {
            ArrayList<Passenger> passengers = new ArrayList<>();
            connection=JDBCUtil.getConnection();
            //获取数据库操作对象
            String sql="select p.*,t.travel_name,t.num from tb_passenger p inner join tb_travel t on t.id=p.tid where t.id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(tid));
            resultSet=preparedStatement.executeQuery();
            //遍历结果集
            while(resultSet!=null&&resultSet.next()){
                String travelName = resultSet.getString("travel_name");
                int num = resultSet.getInt("num");
                String name = resultSet.getString("name");
                int sex = resultSet.getInt("sex");
                String birthday = resultSet.getString("birthday");
                String phone = resultSet.getString("phone");
                String idCard = resultSet.getString("idCard");
                String sign_date = resultSet.getString("sign_date");
                Travel travel = new Travel(travelName, num);
                Passenger passenger = new Passenger(name,sex,birthday,phone,idCard,sign_date,travel);
                passengers.add(passenger);
            }
            return passengers;
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
