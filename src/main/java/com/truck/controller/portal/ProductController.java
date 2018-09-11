package com.truck.controller.portal;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Product;
import com.truck.service.CategoryService;
import com.truck.service.ProductService;
import com.truck.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by master on 2018/3/12.
 */
@Controller
@RequestMapping("/product/")
public class ProductController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "selectById.do")
    @ResponseBody
    //根据产品ID查看详情
    public ServerResponse<ProductDetailVo> selectProductById( Integer productId) {
         return productService.manageProductDetail(productId);
    }

    @RequestMapping("search.do")
    @ResponseBody
    //根据关键字或者分类ID查询产品
    public ServerResponse<PageInfo> search(@RequestParam(value = "productKeyword", required = false) String productKeyword,
                                         @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                         @RequestParam(value = "categoryKeyword", required = false) String categoryKeyword,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "order", defaultValue = "Product_Price") String order,
                                         @RequestParam(value = "by", defaultValue = "desc") String by) {
        //默认不排序
        //order  根据什么排序
        //by  倒序(desc)还是 升序(asc)默认
        //order为空 则 by 不参与
        return productService.getProductByKeywordCategory(productKeyword, categoryId, categoryKeyword,pageNum, pageSize, order, by);
    }

    //根据分类查询商品
    @RequestMapping("selectByCategoryId.do")
    @ResponseBody
    public ServerResponse<List<Product>> selectCatrByid(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") int categoryId) {
        return productService.selectAllByid(categoryId);
    }
}
