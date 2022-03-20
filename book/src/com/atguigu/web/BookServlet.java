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
import java.util.List;

/**
 * @Author Mia
 * Date  2022/2/9 13:39
 * Description
 */
public class BookServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = service.queryBooks();
        req.setAttribute("books", books);
        //跳转至图书管理界面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        Integer pageNo = 100000;
        //调用service添加图书
        service.addBook(book);
        //跳转至bookServlet调用其list方法查询多有图书并跳转至图书馆里界面
        resp.sendRedirect(req.getContextPath() + "/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        //调用service删除图书
        service.deleteBookById(id);
        //跳转至bookServlet调用其list方法查询多有图书并跳转至图书馆里界面
        resp.sendRedirect(req.getContextPath() + "/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        Integer id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用service查询相应图书
        Book book = service.queryBookById(id);
        //将book存在request域中
        req.setAttribute("book", book);
        //跳转至编辑页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        //调用service修改图书
        service.updateBook(book);
        //跳转至图书管理界面
        resp.sendRedirect(req.getContextPath() + "/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        //调用service获取当前分页的相关信息
        Page<Book> page = service.page(pageNo, pageSize);
        page.setUrl("bookServlet?action=page");
        //将分页保存至request域中
        req.setAttribute("page", page);
        //跳转至图书管理界面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
