package com.genersoft.iot.vmp.ext;

import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kerryzhang on 2023/03/05
 */

class ApiPlayUrlExtensionsTest {
    @Test
    public void testUrl() {
        String url = "api://wvp/indexa/中国?code1=3&q=中";

        URI uri = URI.create(url);
        System.out.println(uri);
        System.out.println(uri.getHost());
        System.out.println(uri.getPath());
        System.out.println(uri.getQuery());
        System.out.println(uri.getRawQuery());

    }

}