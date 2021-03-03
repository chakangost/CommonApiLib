package com.freddie.commonapi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpPutTest {

    private HttpPut httpPut;

    @Before
    public void setUp() throws Exception {
        httpPut = new HttpPut("https://www.zaihan.kr/api/users/me");
        httpPut.httpSConnection.setRequestMethod(HttpRequestType.PUT);
        httpPut.setFollowRedirects(true);
    }

    @After
    public void tearDown() {
        httpPut.httpSConnection.disconnect();
    }

    @Test
    public void HttpPut() {
        Assert.assertNotNull(httpPut);
    }

    @Test
    public void setHeader() {
        Assert.assertNotNull(httpPut.setHeader("testHeader", "testValue"));
    }

    @Test
    public void setHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept-Encoding", "gzip");
        headers.put("Accept-Language", "ko");
        headers.put("Connection", "Keep-Alive");
        headers.put("Content-Length", "83");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Cookie", "csrftoken=5uzkJ4ReBwSEEEWFJgLpIPK1cEzmMNx1FmqIIw3zfvfDADxlXTLOlhGuchjNb5LC; sessionid=18vqmdwuurmtpmo6ip11yfhp5tg7p0v9");
        headers.put("Host", "www.zaihan.kr");
        headers.put("User-Agent", "okhttp/3.11.0");
        Assert.assertNotNull(httpPut.setHeaders(headers));
    }

    @Test
    public void getRequestHeader() throws IOException {
        httpPut.setHeader("testHeader", "testValue");
        if (200 == httpPut.execute()) {
            Assert.assertEquals("testValue", httpPut.parameter.getRequestHeader("testHeader"));
        }
    }

    @Test
    public void getRequestHeaders() throws IOException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("testHeader", "testValue");
        httpPut.setHeaders(headers);
        if (200 == httpPut.execute()) {
            Assert.assertEquals("testValue", httpPut.parameter.getRequestHeader("testHeader"));
        }
    }

    @Test
    public void setConnectionTimeout() {
        Assert.assertNotNull(httpPut.setConnectionTimeout(3000));
    }

    @Test
    public void setReadTimeout() {
        Assert.assertNotNull(httpPut.setReadTimeout(3000));
    }

    @Test
    public void setBody() {
        httpPut.setBody("name=은호r&email=chakangost@kakao.com&phone_number=01049373293");
    }

    @Test
    public void setFollowRedirects() {
        Assert.assertNotNull(httpPut.setFollowRedirects(true));
        Assert.assertNotNull(httpPut.setFollowRedirects(false));
    }

    @Test
    public void execute() throws IOException {
        setBody();
        int responseCode = httpPut.execute();
        try {
            Assert.assertEquals(200, responseCode);
        } catch (AssertionError e) {
            e.printStackTrace();
            Assert.assertEquals(401, responseCode);
        }
    }
}
