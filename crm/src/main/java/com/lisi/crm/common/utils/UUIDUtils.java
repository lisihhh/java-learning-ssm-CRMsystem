package com.lisi.crm.common.utils;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 返回一个UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
