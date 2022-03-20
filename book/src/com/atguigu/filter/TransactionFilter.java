package com.atguigu.filter;

import com.atguigu.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Mia
 * @create 2022-02-22-22:14
 */
public class TransactionFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        }
        catch (Exception e){
            //事务回滚
            JdbcUtils.rollbackAndClose();
        }
    }

    @Override
    public void destroy() {

    }
}
