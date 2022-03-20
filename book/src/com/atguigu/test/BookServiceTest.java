package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.serviceImpl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Mia
 * Date  2022/2/9 14:59
 * Description
 */
public class BookServiceTest {
    private BookService service = new BookServiceImpl();
    @Test
    public void addBook() {
        Book book = new Book(null,"aaa",20.0,"a",12,90,null);
        service.addBook(book);
    }
}