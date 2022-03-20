package com.atguigu.service.serviceImpl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.daoImpl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

/**
 * @Author Mia
 * Date  2022/2/9 13:42
 * Description
 */
public class BookServiceImpl implements BookService {
    private BookDao dao = new BookDaoImpl();
    @Override
    public List<Book> queryBooks() {
        return dao.queryBooks();
    }

    @Override
    public void addBook(Book book) {
        dao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        dao.deleteBookById(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        return dao.queryBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        dao.updateBook(book);
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        page.setPageSize(pageSize);
        Integer pageTotalCount = dao.queryForPageTotalCount();
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount%pageSize > 0)
            pageTotal += 1;
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageTotalCount(pageTotalCount);
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book> items = dao.queryForItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);
        page.setPageTotalCount(dao.queryForPageTotalCount(min,max));
        Integer pageTotal = dao.queryForPageTotalCount(min,max)/pageSize;
        if(dao.queryForPageTotalCount()%pageSize != 0)
            pageTotal += 1;
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        Integer begin = (page.getPageNo()-1)*pageSize;
        page.setItems(dao.queryForPageItems(begin,pageSize,min,max));
        return page;
    }
}
