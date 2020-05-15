package com.example.commoncustomizecore.api.commons;

import java.util.UUID;

public class CommonCoreUtils
{
    public static String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
