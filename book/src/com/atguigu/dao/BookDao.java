package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @Author Mia
 * Date  2022/2/9 11:53
 * Description
 */
public interface BookDao{
    //添加图书
    int addBook(Book book);

    //删除图书
    int deleteBookById(Integer id);

    //修改图书
    int updateBook(Book book);

    //根据Id查找一本图书
    Book queryBookById(Integer id);

    //查找所有图书
    List<Book> queryBooks();

    //总记录数
    Integer queryForPageTotalCount();

    //当前页数据
    List<Book> queryForItems(int begin,int pageSize);

    //查找某一区间内的总记录数
    Integer queryForPageTotalCount(int min,int max);

    //查找某一区间内的当前页数据
    List<Book> queryForPageItems(int begin,int pageSize,int min,int max);
}
