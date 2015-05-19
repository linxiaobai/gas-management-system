package com.gms.service;

import com.gms.bean.po.RepairedRecord;

/**
 * Created by Kevin on 2015/5/10.
 */
public interface RepairedRecordService {
    void addRepairedRecord(RepairedRecord repairedRecord);

    String queryAll();
}
