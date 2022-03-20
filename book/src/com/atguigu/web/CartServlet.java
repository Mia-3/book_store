package com.atguigu.web;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.daoImpl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;
import sun.awt.windows.WEmbeddedFrame;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mia
 * @create 2022-02-20-17:03
 */
public class CartServlet extends BaseServlet{
    private BookDao dao = new BookDaoImpl();
    //加入购物车
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        //到数据库中查询图书
        Book book = dao.queryBookById(id);
        //将图书项转换为购物车项
        CartItem item = new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
        //将最后一个加入的图书名存入request域中
        req.getSession().setAttribute("lastBook",book.getName());
        //获取购物车
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //将商品项加入购物车
        cart.addItem(item);
        //跳转至刚刚点击的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    //删除商品项
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        //获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //删除商品
        cart.deleteItem(id);
        //跳转至刚刚点击的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    //清空购物车
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //清空购物车
        cart.clear();
        //跳转至刚刚点击的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    //修改数量
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数
        Integer count = WebUtils.parseInt(req.getParameter("count"),0);
        Integer id  = WebUtils.parseInt(req.getParameter("id"),0);
        //获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //修改数量
        cart.updateCount(id,count);
        //跳转至刚刚发起请求的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        //到数据库中查询图书
        Book book = dao.queryBookById(id);
        //将图书项转换为购物车项
        CartItem item = new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
        //获取购物车
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //将商品项加入购物车
        cart.addItem(item);
        //将最后一个添加的商品名存入request域中
        req.setAttribute("lastName",item.getName());
        //解决乱码
        resp.setContentType("text/html;charset=UTF-8");
        //返回总商品数量和最后一个添加的商品名
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",item.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

}
