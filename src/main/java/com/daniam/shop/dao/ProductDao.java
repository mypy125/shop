package com.daniam.shop.dao;

import com.daniam.shop.domain.Product;
import org.hibernate.SessionFactory;

public class ProductDao extends BaseDao<Long, Product>{
    public ProductDao(SessionFactory session){
        super(session, Product.class);
    }
}
