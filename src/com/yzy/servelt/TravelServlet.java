package com.yzy.servelt;

import com.yzy.pojo.Passenger;
import com.yzy.pojo.Travel;
import com.yzy.service.PassengerService;
import com.yzy.service.TravelService;
import com.yzy.service.impl.PassengerServiceImpl;
import com.yzy.service.impl.TravelServiceImpl;
import javafx.scene.control.PasswordField;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/travel")
public class TravelServlet extends HttpServlet {
    private TravelService travelService=new TravelServiceImpl();
    private PassengerService passengerService=new PassengerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String flag=req.getParameter("flag");
        if(flag!=null&&flag.equals("list")){
            this.getTravelList(req,resp);
        }
        else if(flag!=null&&flag.equals("toAdd")){
            this.addPassenger(req,resp);
        }
        else if(flag!=null&&flag.equals("findTravelById")){
            this.findTravelById(req,resp);
        }
        else if(flag!=null&&flag.equals("toUpdateById")){
            this.toUpdateById(req,resp);
        }
        else if(flag!=null&&flag.equals("deleteTravelByIds")){
            this.deleteTravelByIds(req,resp);
        }
        else if(flag!=null&&flag.equals("findTravelAndPassenger")){
            this.findTravelAndPassenger(req,resp);
        }
    }

    public void findTravelAndPassenger(HttpServletRequest req, HttpServletResponse resp) {
        String tid = req.getParameter("tid");
        List<Passenger> passengerList=passengerService.findTravelAndPassenger(tid);
        req.setAttribute("passengerList",passengerList);
        try {
            req.getRequestDispatcher("/jsp/findPassenger.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量删除
    //执行批量删除时，如果有一条线路有游客报名，那就不能进行删除，如果无游客报名，则可以删除
    //flag=1时，成功执行删除
    //flag=2时，有至少一条线路有游客报名，不能删除
    //flag=3时，程序发生了异常或删除失败
    public void deleteTravelByIds(HttpServletRequest req, HttpServletResponse resp) {
        String ids = req.getParameter("ids");
        int flag=travelService.deleteTravelByIds(ids);
        if(flag==1){
            //向域中添加一个删除成功的标识
           req.setAttribute("deleteSuccess",true);
            try {
                req.getRequestDispatcher("/travel?flag=list").forward(req,resp);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        }else if(flag==2){
            //向域中添加一个已有游客报名的表识
            req.setAttribute("passenger",true);
            try {
                req.getRequestDispatcher("/travel?flag=list").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("删除失败！");
        }
    }

    public void toUpdateById(HttpServletRequest req, HttpServletResponse resp) {
        String tid = req.getParameter("tid");
        String travelName = req.getParameter("travelName");
        String travelDesc = req.getParameter("travelDesc");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String tripStart = req.getParameter("tripStart");
        String tripEnd = req.getParameter("tripEnd");
        String price = req.getParameter("price");
        Travel travel=new Travel(Integer.parseInt(tid),travelName,travelDesc,startDate,endDate,tripStart,tripEnd,Double.valueOf(price));

        boolean flag=this.travelService.toUpdateById(travel);
        if(flag){
            try {
                resp.sendRedirect("http://localhost:8080/web/travel?flag=list");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("修改失败！");
        }
    }

    public void findTravelById(HttpServletRequest req, HttpServletResponse resp) {
        String tid = req.getParameter("tid");
        Travel travel=travelService.findTravelById(tid);
        req.setAttribute("travel",travel);
        try {
            req.getRequestDispatcher("/jsp/travelUpdate.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPassenger(HttpServletRequest req, HttpServletResponse resp) {
        String tid = req.getParameter("tid");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String idCard = req.getParameter("idCard");
        Passenger passenger = new Passenger(name, Integer.parseInt(sex), birthday, phone, idCard,Integer.parseInt(tid));
        boolean flag=this.passengerService.addPassenger(passenger);
        if(flag){
            try {
                resp.sendRedirect("http://localhost:8080/web/travel?flag=list");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("报名失败！");
        }
    }

    //获得旅游线路列表
    public void getTravelList(HttpServletRequest req, HttpServletResponse resp) {
        List<Travel> travelList=this.travelService.getTravelList();
        req.setAttribute("travelList",travelList);
        try {
            //转发
            req.getRequestDispatcher("/jsp/travelList.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
