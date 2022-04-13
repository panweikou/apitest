package O2O.util;


import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.MultiPartContentProvider;
import org.eclipse.jetty.client.util.PathContentProvider;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
/**
 * @Author: Sean_Pan
 * @ClassName HttpSender
 * @Description TODO
 * @date 11/29/2021 4:52 PM
 * @Version 1.0
 */
public class HttpSender {
    private static final Logger logger = LoggerFactory.getLogger(HttpSender.class);
    private static final int CONNECT_TIME_OUT = 30;
    private static final HttpSender httpSender = new HttpSender();

    private HttpSender() {
        HttpClient httpClient = newHttpClient();
    }

    private HttpClient newHttpClient() {
        HttpClient httpClient = new HttpClient(new SslContextFactory(true));
        try {
            httpClient.start();
        } catch (Exception e) {
            throw new RuntimeException( "Exception creating httpClient");
        }
        return httpClient;
    }

    public static HttpSender getSender() {return httpSender;}

    public ContentResponse sendPostRequestWithAuthentication(String URL, String json, String adminAccount) throws Exception {
        return newHttpClientRequest(httpClient ->
                httpClient.newRequest(URL)
                    .method(HttpMethod.POST)
                    .timeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .content(new StringContentProvider(json), "application/json")
                    .header(HttpHeader.AUTHORIZATION, "Basic" + O2OUtil.getCredentials(adminAccount))
                );
    }

    public ContentResponse sendPostRequest(String URL, String json) throws Exception {
        return newHttpClientRequest(httpClient ->
                httpClient.newRequest(URL)
                    .method(HttpMethod.POST)
                    .timeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .content(new StringContentProvider(json), "application/json")
                );
    }

    public ContentResponse sendGetRequestWithAuthentication(String URL, String adminAccount) throws Exception {
        return newHttpClientRequest(httpClient ->
                httpClient.newRequest(URL)
                    .method(HttpMethod.GET)
                    .timeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .header(HttpHeader.AUTHORIZATION, "Basic" + O2OUtil.getCredentials(adminAccount))
                );
    }

    public ContentResponse sendPutRequestByFileWithCookie(String URL, String cookie, String filePath) throws Exception {
        MultiPartContentProvider multiPart = new MultiPartContentProvider();
        multiPart.addFilePart("file", "uploadFile" , new PathContentProvider(Paths.get(filePath)), new HttpFields());
        multiPart.close();
        return newHttpClientRequest(httpClient ->
                httpClient.newRequest(URL)
                    .method(HttpMethod.GET)
                    .timeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .content(multiPart)
                    .header(HttpHeader.COOKIE, cookie)
                );
    }


    private ContentResponse newHttpClientRequest(Function<HttpClient, Request> function) throws Exception {
        HttpClient httpClient = null;
        try {
            httpClient = newHttpClient();
            Request request = function.apply(httpClient);
            StringBuilder bodyContent = new StringBuilder();
            if (null != request.getContent()) {
                ContentProvider content = request.getContent();
                for (ByteBuffer next : content) {
                    final byte[] bytes = new byte[next.capacity()];
                    next.get(bytes);
                    bodyContent.append(new String(bytes, StandardCharsets.UTF_8)).append("\n");
                }
                logger.info("the request URL is {}, request body is {}", request.getURI().toString(), bodyContent);
            }
            else {
                logger.info("the request URL is {} ", request.getURI().toString());
            }
            ContentResponse contentResponse = request.send();
            int status = contentResponse.getStatus();
            if (HttpStatus.isSuccess(status)) {
                logger.info("Received response status: {}, response body: {}", contentResponse.getStatus(), ConvertUtil.unicodeStrToString(contentResponse.getContentAsString()));
                return contentResponse;
            }
            else {
                throw new CustomerException("the status code is not between 200 to 299, the response is" + contentResponse.getContentAsString());
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            stop(httpClient);
        }
    }

    private void stop(HttpClient httpClient) {
        try {
            if (httpClient != null) {
                httpClient.stop();
                httpClient.destroy();
            }
        } catch (Exception e) {
            logger.error("Errors when stopping httpclient {}", e);
        }
    }

}
