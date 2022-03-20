package com.atguigu.pojo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mia
 * @create 2022-02-20-16:05
 */
public class Cart {

    private Map<Integer,CartItem> items = new LinkedHashMap<>();


    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }

    //添加商品项
    public void addItem(CartItem cartItem){
        //判断购物车中是否有此商品
        CartItem item = items.get(cartItem.getId());
        if(item != null){
            item.setCount(item.getCount()+1);
        }
        else
            items.put(cartItem.getId(),cartItem);
    }

    //删除商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }

    //清空购物车
    public void clear(){
        items.clear();
    }

    //修改商品数量
    public void updateCount(Integer id,Integer count){
        CartItem item = items.get(id);
        item.setCount(count);
    }

    //获得商品总数
    public Integer getTotalCount(){
        Integer totalCount = 0;
        for(CartItem item:items.values()) {
            totalCount += item.getCount();
        }
        return totalCount;
    }

    public Double getTotalPrice(){
        Double totalPrice = 0.0;
        for(Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalPrice += entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

}
