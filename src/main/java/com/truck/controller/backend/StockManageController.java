package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.Stock;
import com.truck.service.IAdminService;
import com.truck.service.IStockService;
import com.truck.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by geely
 */

@Controller
@RequestMapping("/manage/stock")
public class StockManageController {

    @Autowired
    private IAdminService iAdminService;
    @Autowired
    private IStockService iStockService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse stockSave(HttpSession session, Stock stock){
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iStockService.saveOrUpdateStock(admin.getAdminId(),stock);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    @RequestMapping("set_stock_status.do")
    @ResponseBody
    public ServerResponse setStockStatus(HttpSession session, Integer stockId, Integer status){
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iStockService.setStockStatus(stockId,status);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    //查看某个产品的某个库存的详情
    public ServerResponse getDetail(HttpSession session, Integer stockId){
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
            return iStockService.getStockDetail(stockId);

    }

    @RequestMapping("list.do")
    @ResponseBody
    //查看某个产品的所有库存
    public ServerResponse getList(HttpSession session,
                                  @RequestParam(value = "productId",required = false)Integer productId,
                                  @RequestParam(value = "warehouseId",required = false)Integer warehouseId,
                                  @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
            return iStockService.selectListByProductIdWarehouseId(admin.getAdminId(),productId,warehouseId,pageNum,pageSize);
    }

    @RequestMapping("reduce.do")
    @ResponseBody
    //减少某个产品的库存
    public ServerResponse reduce(HttpSession session, Integer stockId, Integer reduceNum){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iStockService.reduceStockNum(stockId,reduceNum);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    @RequestMapping("add.do")
    @ResponseBody
    //减少某个产品的库存
    public ServerResponse add(HttpSession session, Integer stockId, Integer addNum){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iStockService.addStockNum(stockId,addNum);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }


/*    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse stockSearch(HttpSession session,String stockName,Integer stockId, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
            return iStockService.searchStock(stockName,stockId,pageNum,pageSize);
    }*/

}
