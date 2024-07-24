package com.daniam.shop.dao;

import com.daniam.shop.domain.Stock;
import org.hibernate.SessionFactory;

public class StockDao extends BaseDao<Long, Stock>{
    public StockDao(SessionFactory session){
        super(session, Stock.class);
    }
}
