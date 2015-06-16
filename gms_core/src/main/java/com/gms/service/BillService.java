package com.gms.service;

import com.gms.bean.po.Bill;

/**
 * Created by Kevin on 2015/6/8.
 */
public interface BillService {
    String createOrder(Bill bill);

    String queryAllUnPaidBill();

    String dealBill(String orderSequence, Double payMoney);
}
