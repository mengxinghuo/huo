package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.StockSend;
import com.truck.service.IAdminService;
import com.truck.service.IOrderService;
import com.truck.vo.OrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order/manage/")
public class OrderManageController {

    private static  final Logger logger = LoggerFactory.getLogger(OrderManageController.class);

    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IAdminService iAdminService;

    /**
     * 查询所有订单列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpSession session,
                                              @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()){
            return iOrderService.manageList(pageNum, pageSize);
        }
         else{
            return iOrderService.manageList(admin.getAdminId(),pageNum, pageSize);
        }
    }

/*    *//**
     * 查询订单详情
     * @param session
     * @param orderNo
     * @return
     *//*
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(HttpSession session, String orderNo){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        return iOrderService.manageDetail(admin.getAdminId(),orderNo);
    }*/

    /**
     * 搜索订单，可进行模糊查询，有分页
     * @param session
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpSession session, String orderNo,
                                                @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }

        if (iAdminService.checkAdminRole(admin).isSuccess()){
            return iOrderService.manageSearch(orderNo, pageNum, pageSize);
        }
        else{
            return iOrderService.manageSearchShop(admin.getAdminId(),orderNo,pageNum, pageSize);
        }
    }

    /**
     * 发货
     * @param session
     * @return
     */
    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpSession session, Integer orderDetailId , String stockSendList){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        logger.info("传过来的参数:{}",stockSendList);
        return iOrderService.manageSendGoods(orderDetailId,stockSendList);
    }

    /**
     * 查询该店的客户信息
     * @param session
     * @return
     */
    @RequestMapping("get_shop_customer.do")
    @ResponseBody
    public ServerResponse getShopCustomers(HttpSession session){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()){
            return iOrderService.getCustomers();
        }
        else{
            return iOrderService.getShopCustomers(admin.getAdminId());
        }

    }

    /**
     * 根据userId shopId 查询 订单
     * @param userId
     * @param
     * @return
     */
    @RequestMapping("get_customer_order.do")
    @ResponseBody
    public ServerResponse getCustomerOrders(HttpSession session , Integer userId){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()){
            return iOrderService.getCustomerOrder(userId);
        }
        else{
            return iOrderService.getCustomerOrders(userId,admin.getAdminId());
        }
    }

}
