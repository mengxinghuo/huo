package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.Location;
import com.truck.pojo.User;
import com.truck.service.IAdminService;
import com.truck.service.ILocationService;
import com.truck.vo.LocationVo;
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
@RequestMapping("/manage/location/")
public class LocationManageController {


    @Autowired
    private ILocationService iLocationService;
    @Autowired
    private IAdminService iAdminService;


    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, LocationVo locationVo) {
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iLocationService.add(locationVo);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }



//    @RequestMapping("del.do")
//    @ResponseBody
//    public ServerResponse del(HttpSession session, Integer logisticsId) {
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
//        return iLocationService.del( logisticsId);
//    }
//
//    @RequestMapping("update.do")
//    @ResponseBody
//    public ServerResponse update(HttpSession session, Location location) {
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
//        return iLocationService.update( location);
//    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         HttpSession session ,Integer logisticsId) {
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        return iLocationService.list(logisticsId, pageNum, pageSize);
    }

    @RequestMapping("listStation.do")
    @ResponseBody
    public ServerResponse<PageInfo> listStation(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         HttpSession session ,Integer logisticsId) {
         Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        return iLocationService.listStation(logisticsId, pageNum, pageSize);
    }


}
