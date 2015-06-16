package com.gms.service;

import com.gms.service.impl.OrderSequenceService;
import org.junit.Test;

/**
 * Created by Kevin on 2015/6/8.
 */
public class OrderSequenceServiceTest {
    @Test
    public void testSelect() {
        OrderSequenceService orderSequenceService = new OrderSequenceService();
        System.out.println(orderSequenceService.fetchOrderSequence());
    }
}
