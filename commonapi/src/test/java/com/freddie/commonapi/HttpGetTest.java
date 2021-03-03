package com.freddie.commonapi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpGetTest {

    private HttpGet httpGet;

    @Before
    public void setUp() throws Exception {
        httpGet = new HttpGet("https://en.wikipedia.org/api/rest_v1/page/summary/Titanic");
        httpGet.httpSConnection.setRequestMethod(HttpRequestType.GET);
        httpGet.setFollowRedirects(true);
    }

    @After
    public void tearDown() {
        httpGet.httpSConnection.disconnect();
    }

    @Test
    public void HttpGet() {
        Assert.assertNotNull(httpGet);
    }

    @Test
    public void setHeader() {
        Assert.assertNotNull(httpGet.setHeader("testHeader", "testValue"));
    }

    @Test
    public void setHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("testHeader1", "testValue1");
        headers.put("testHeader2", "testValue2");
        Assert.assertNotNull(httpGet.setHeaders(headers));
    }

    @Test
    public void getRequestHeader() throws IOException {
        httpGet.setHeader("testHeader", "testValue");
        if (200 == httpGet.execute()) {
            Assert.assertEquals("testValue", httpGet.parameter.getRequestHeader("testHeader"));
        }
    }

    @Test
    public void getRequestHeaders() throws IOException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("testHeader", "testValue");
        httpGet.setHeaders(headers);
        if (200 == httpGet.execute()) {
            Assert.assertEquals("testValue", httpGet.parameter.getRequestHeader("testHeader"));
        }
    }

    @Test
    public void setConnectionTimeout() {
        Assert.assertNotNull(httpGet.setConnectionTimeout(3000));
    }

    @Test
    public void setReadTimeout() {
        Assert.assertNotNull(httpGet.setReadTimeout(3000));
    }

    @Test
    public void setBody() {
        try {
            httpGet.setBody("body");
        } catch (UnsupportedOperationException e) {
            Assert.assertEquals("Not supported for HTTP GET", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void setFollowRedirects() {
        Assert.assertNotNull(httpGet.setFollowRedirects(true));
        Assert.assertNotNull(httpGet.setFollowRedirects(false));
    }

    @Test
    public void execute() throws IOException {
        Assert.assertEquals(200, httpGet.execute());
    }

    @Test
    public void getResponseBody() throws IOException {
        if (200 == httpGet.execute()) {
            Assert.assertNotNull(httpGet.getResponseBody());
        }
    }
}
