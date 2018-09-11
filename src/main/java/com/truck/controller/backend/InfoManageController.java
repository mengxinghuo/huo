package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.Info;
import com.truck.pojo.User;
import com.truck.service.IAdminService;
import com.truck.service.IInfoService;
import com.truck.vo.InfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created
 */
@Controller
@RequestMapping("/manage/info/")
public class InfoManageController {


    @Autowired
    private IInfoService iInfoService;
    @Autowired
    private IAdminService iAdminService;


    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, InfoVo infoVo) {
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iInfoService.add(admin.getAdminId(), infoVo);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }



    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse del(HttpSession session, Integer logisticsId) {
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iInfoService.del(admin.getAdminId(), logisticsId);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, InfoVo infoVo) {
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iInfoService.update(admin.getAdminId(), infoVo);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }


    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse<InfoVo> select(HttpSession session, Integer logisticsId) {
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        return iInfoService.select(admin.getAdminId(), logisticsId);
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         HttpSession session,
                                         String logisticsNo,
                                         Integer orderId) {
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        return iInfoService.list(admin.getAdminId(),logisticsNo, orderId,pageNum, pageSize);
    }


}
