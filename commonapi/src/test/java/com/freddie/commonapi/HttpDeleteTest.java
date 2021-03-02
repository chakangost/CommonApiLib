package com.freddie.commonapi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpDeleteTest {

    private HttpDelete httpDelete;

    @Before
    public void setUp() throws Exception {
        httpDelete = new HttpDelete("https://www.zaihan.kr/api/posts/acPQwo5xUR");
        httpDelete.connection.setRequestMethod(HttpRequestType.DELETE);
    }

    @After
    public void tearDown() {
        httpDelete.connection.disconnect();
    }

    @Test
    public void HttpDelete() {
        Assert.assertNotNull(httpDelete);
    }

    @Test
    public void setHeader() {
        Assert.assertNotNull(httpDelete.setHeader("testHeader", "testValue"));
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
        Assert.assertNotNull(httpDelete.setHeaders(headers));
    }

    @Test
    public void setConnectionTimeout() {
        Assert.assertNotNull(httpDelete.setConnectionTimeout(3000));
    }

    @Test
    public void setReadTimeout() {
        Assert.assertNotNull(httpDelete.setReadTimeout(3000));
    }

    @Test
    public void setBody() {
        try {
            httpDelete.setBody("body");
        } catch (UnsupportedOperationException e) {
            Assert.assertEquals("Not supported for HTTP DELETE", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void setFollowRedirects() {
        Assert.assertNotNull(httpDelete.setFollowRedirects(true));
        Assert.assertNotNull(httpDelete.setFollowRedirects(false));
    }

    @Test
    public void execute() throws IOException {
        try {
            Assert.assertEquals(200, httpDelete.execute());
        } catch (AssertionError e) {
            e.printStackTrace();
            Assert.assertEquals(401, httpDelete.execute());
        }
    }
}
