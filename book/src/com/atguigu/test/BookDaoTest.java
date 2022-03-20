package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.daoImpl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.utils.WebUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author Mia
 * Date  2022/2/9 12:49
 * Description
 */
public class BookDaoTest {
    private BookDao dao = new BookDaoImpl();

    @Test
    public void addBook() {
        Map map = new HashMap();
        map.put("id",null);
        map.put("name","时间简史");
        map.put("price",80);
        map.put("author","霍金");
        map.put("sales",100);
        map.put("stock",0);
        map.put("imgPath",null);
        Book book = new Book();
        WebUtils.copyParamToBean(map,book);
        dao.addBook(book);
    }

    @Test
    public void deleteBookById() {
        dao.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        Book book = new Book(22,"时间简史",30.0,"霍金",90,10,null);
        dao.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book =dao.queryBookById(3);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = dao.queryBooks();
        for(Book book:books){
            System.out.println(book);
        }
    }
}