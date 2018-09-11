package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.Status;
import com.truck.pojo.User;
import com.truck.service.IAdminService;
import com.truck.service.IStatusService;
import com.truck.vo.StatusVo;
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
@RequestMapping("/manage/status/")
public class StatusManageController {


    @Autowired
    private IStatusService iStatusService;
    @Autowired
    private IAdminService iAdminService;


    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, StatusVo statusVo) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        if (iAdminService.checkShopStatus(admin).isSuccess()){
            return iStatusService.add(statusVo);
        }else{
            return iAdminService.checkShopStatus(admin);
        }
    }


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         HttpSession session ,Integer logisticsId) {
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"店铺用户未登录，请登录");
        }
        return iStatusService.list(logisticsId, pageNum, pageSize);
    }


}
