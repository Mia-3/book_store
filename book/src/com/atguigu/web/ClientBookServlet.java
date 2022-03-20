package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.serviceImpl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * @author Mia
 * @create 2022-02-20-14:52
 */
public class ClientBookServlet extends BaseServlet{
    private BookService service = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用service获取当前分页的相关信息
        Page<Book> page = service.page(pageNo, pageSize);
        page.setUrl("clientBookServlet?action=page");
        //将分页保存至request域中
        req.setAttribute("page", page);
        //跳转至前台界面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数
        Integer min = WebUtils.parseInt(req.getParameter("min"),0);
        Integer max = WebUtils.parseInt(req.getParameter("max"),10000);
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        //调用service获取page
        Page<Book> page = service.pageByPrice(pageNo,pageSize,min,max);
        page.setUrl("clientBookServlet?action=pageByPrice");
        //将page存入request域中
        req.setAttribute("page",page);
        //跳转至首页
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
