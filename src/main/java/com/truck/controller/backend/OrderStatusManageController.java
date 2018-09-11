package com.truck.controller.backend;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.*;
import com.truck.service.IAdminService;
import com.truck.service.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created
 */
@Controller
@RequestMapping("/manage/order/status/")
public class OrderStatusManageController {


    @Autowired
    private IOrderStatusService iOrderStatusService;
    @Autowired
    private IAdminService iAdminService;


    /**
     * 中间表增加订单状态
     * @param session
     * @param orderBuyStatus
     * @return
     */
    @RequestMapping("add_order_buy.do")
    @ResponseBody
    public ServerResponse addOrderBuy(HttpSession session, OrderBuyStatus orderBuyStatus) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iOrderStatusService.addOrderBuy(orderBuyStatus);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    /**
     * 中间表增加支付状态
     * @param session
     * @param orderPayStatus
     * @return
     */
    @RequestMapping("add_order_pay.do")
    @ResponseBody
    public ServerResponse addOrderPay(HttpSession session, OrderPayStatus orderPayStatus) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iOrderStatusService.addOrderPay(orderPayStatus);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    /**
     * 增加订单状态基础数据
     * @param session
     * @param buyStatus
     * @return
     */
    @RequestMapping("add_buy.do")
    @ResponseBody
    public ServerResponse addBuy(HttpSession session, BuyStatus buyStatus) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return iOrderStatusService.addBuy(buyStatus);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }

    /**
     * 增加支付状态基础数据
     * @param session
     * @param payStatus
     * @return
     */
    @RequestMapping("add_pay.do")
    @ResponseBody
    public ServerResponse addPay(HttpSession session, PayStatus payStatus) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return iOrderStatusService.addPay(payStatus);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }


//    @RequestMapping("list_buy.do")
//    @ResponseBody
//    public ServerResponse<PageInfo> listBuy(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
//                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
//                                         HttpSession session ) {
//        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
//        if(admin == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
//        }
//        return iOrderStatusService.listBuy(logisticsId, pageNum, pageSize);
//    }

    @RequestMapping("list_buy.do")
    @ResponseBody
    public ServerResponse listBuy(HttpSession session ) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        return iOrderStatusService.listBuy();
    }

    @RequestMapping("list_pay.do")
    @ResponseBody
    public ServerResponse listPay(HttpSession session ) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        return iOrderStatusService.listPay();
    }


}
