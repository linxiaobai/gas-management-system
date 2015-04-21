package com.gms.util;

import com.gms.util.dbutil.SQLUtil;
import org.junit.Test;

/**
 * Created by Kevin on 2015/4/19.
 */
public class SQLTest {
    @Test
    public void update() {
//        String sql = "update menus set IS_VALID = 1 where ID in (?, ?, ?)";
//        SQLUtil.batch(sql, new Object[][]{{1,2,3}});
        String sql = "UPDATE MENUS SET IS_VALID = ?";
        SQLUtil.updateOne(sql,1);

    }
}
