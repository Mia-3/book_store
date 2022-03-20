package com.atguigu.dao.daoImpl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @Author Mia
 * Date  2022/2/9 11:53
 * Description
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(name,price,author,sales,stock) VALUES(?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "DELETE FROM t_book WHERE id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE t_book SET name=?,price=?,author=?,sales=?,stock=? WHERE id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "SELECT id,name,price,author,sales,stock,img_path imgPath FROM t_book WHERE id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "SELECT id,name,price,author,sales,stock,img_path imgPath FROM t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "SELECT count(*) FROM t_book";
        Number count = (Number)queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql = "SELECT id,name,author,price,sales,stock,img_path imgPath FROM t_book LIMIT ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCount(int min, int max) {
        String sql = "SELECT count(*) FROM t_book WHERE price BETWEEN ? AND ?";
        Number count = (Number)queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, int min, int max) {
        String sql = "SELECT id,name,author,price,sales,stock,img_path imgPath FROM t_book WHERE price BETWEEN ? AND ? ORDER BY price LIMIT ?,? ";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
