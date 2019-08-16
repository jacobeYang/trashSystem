package com.jacob.trash.util;

import java.util.UUID;

public class IDGenerator {
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

}
