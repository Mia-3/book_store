package com.atguigu.service;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @Author Mia
 * Date  2022/2/9 13:40
 * Description
 */
public interface BookService {
    //查找全部图书
    List<Book> queryBooks();

    //添加图书
    void addBook(Book book);

    //根据图书id删除图书
    void deleteBookById(Integer id);

    //根据图书di查询图书
    Book queryBookById(Integer id);

    //更新图书
    void updateBook(Book book);

    //处理分页，返回相应的那一页(page)
    Page<Book> page(int pageNo,int pageSize);

    //处理有价格条件的分页，返回当前页
    Page pageByPrice(int pageNo,int pageSize,int min,int max);
}
