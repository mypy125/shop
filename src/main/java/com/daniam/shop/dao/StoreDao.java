package com.daniam.shop.dao;

import com.daniam.shop.domain.Store;
import org.hibernate.SessionFactory;

public class StoreDao extends BaseDao<Long, Store> {
    public StoreDao(SessionFactory session){
        super(session, Store.class);
    }
}
