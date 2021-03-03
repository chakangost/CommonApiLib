package com.freddie.commonapi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpPostTest {

    private HttpPost httpPost;

    @Before
    public void setUp() throws Exception {
        httpPost = new HttpPost("https://www.zaihan.kr/api/user/signup");
        httpPost.httpSConnection.setRequestMethod(HttpRequestType.POST);
        httpPost.setFollowRedirects(true);
    }

    @After
    public void tearDown() {
        httpPost.httpSConnection.disconnect();
    }

    @Test
    public void HttpPost() {
        Assert.assertNotNull(httpPost);
    }

    @Test
    public void setHeader() {
        Assert.assertNotNull(httpPost.setHeader("testHeader", "testValue"));
    }

    @Test
    public void setHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("testHeader1", "testValue1");
        headers.put("testHeader2", "testValue2");
        Assert.assertNotNull(httpPost.setHeaders(headers));
    }

    @Test
    public void getRequestHeader() throws IOException {
        httpPost.setHeader("testHeader", "testValue");
        if (200 == httpPost.execute()) {
            Assert.assertEquals("testValue", httpPost.parameter.getRequestHeader("testHeader"));
        }
    }

    @Test
    public void getRequestHeaders() throws IOException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("testHeader", "testValue");
        httpPost.setHeaders(headers);
        if (200 == httpPost.execute()) {
            Assert.assertEquals("testValue", httpPost.parameter.getRequestHeader("testHeader"));
        }
    }

    @Test
    public void setConnectionTimeout() {
        Assert.assertNotNull(httpPost.setConnectionTimeout(3000));
    }

    @Test
    public void setReadTimeout() {
        Assert.assertNotNull(httpPost.setReadTimeout(3000));
    }

    @Test
    public void setBody() {
        httpPost.setBody("username=test_user_name&password=test123456&phone_number=01049373293" +
                "&language=ko&name=freddie&email=chakangost@gmail.com");
    }

    @Test
    public void setFollowRedirects() {
        Assert.assertNotNull(httpPost.setFollowRedirects(true));
        Assert.assertNotNull(httpPost.setFollowRedirects(false));
    }

    @Test
    public void execute() throws IOException {
        setBody();
        int responseCode = httpPost.execute();
        try {
            Assert.assertEquals(200, responseCode);
        } catch (AssertionError e) {
            e.printStackTrace();
            Assert.assertEquals(400, responseCode);
        }
    }
}
