package com.mmall.controller.backen;


import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.util.PropertyUtil;
import com.mmall.vo.ProductDetailVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;



//        pageNum(default=1)
//        pageSize(default=10)


@Controller
@RequestMapping("/manage/product/")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse response = iUserService.checkeAdmin(user);
        if (response.isSuccess()) {
            return  iProductService.list(pageNum, pageSize);
        }
        return response;
    }


    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse search(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                 String productName, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse response = iUserService.checkeAdmin(user);
        if (response.isSuccess()) {
            return  iProductService.search(pageNum, pageSize, productName, productId);
        }
        return response;
    }

//    upload.do

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", defaultValue = "1") MultipartFile file,
                                 HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse response = iUserService.checkeAdmin(user);
        if (response.isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetName = iFileService.upload(path, file);
            if (StringUtils.isNotBlank(targetName)) {
                Map<String,String> responseMap = Maps.newHashMap();
                responseMap.put("uri", targetName);
                responseMap.put("url", PropertyUtil.getProperty("ftp.server.http.prefix") + targetName);
                return ServerResponse.createBySuccess(responseMap);
            } else {
                return ServerResponse.createByErrorMsg("上传文件服务器失败");
            }
        }
        return response;
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse detail(HttpSession session, Integer productId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse response = iUserService.checkeAdmin(user);
        if (response.isSuccess()) {
            return iProductService.listProductDetail(productId);
        }
        return response;
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId, Integer status) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse response = iUserService.checkeAdmin(user);
        if (response.isSuccess()) {
            return iProductService.setStatus(productId, status);
        }
        return response;
    }


    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse saveOrUpdateProduct(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse response = iUserService.checkeAdmin(user);
        if (response.isSuccess()) {
            return iProductService.saveOrUpdate(product);
        }
        return response;
    }
































































}
