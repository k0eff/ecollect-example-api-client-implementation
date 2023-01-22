package org.ecollect.api.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class Connection {

    ApiSettings settings;


    public Connection (ApiSettings sett) {
        this.settings = sett;
    }



    public String submitPostRequest(String uri, String jsonData) throws IOException {
        RequestConfig config = createRequestConfig();

        try (CloseableHttpClient client =  HttpClientBuilder.create().setDefaultRequestConfig(config).build();) {
            HttpPost request = new HttpPost(this.settings.getBaseUrl()+uri);
            request.setHeader("Content-Type", "application/json; charset=UTF-8");
            request.setHeader("Accept-Charset", "UTF-8");
            request.setHeader("Authorization", "Bearer "+ this.settings.getAuthToken());
            request.setEntity(new StringEntity(jsonData, Charset.forName("UTF-8")));

            HttpResponse response = client.execute(request);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            throw e;
        }
    }


    public String submitPatchRequest(String uri, String jsonData) throws IOException {
        RequestConfig config = createRequestConfig();

        try (CloseableHttpClient client =  HttpClientBuilder.create().setDefaultRequestConfig(config).build();) {
            HttpPatch request = new HttpPatch(this.settings.getBaseUrl()+uri);
            request.setHeader("Content-Type", "application/json; charset=UTF-8");
            request.setHeader("Accept-Charset", "UTF-8");
            request.setHeader("Authorization", "Bearer "+ this.settings.getAuthToken());
            request.setEntity(new StringEntity(jsonData, Charset.forName("UTF-8")));

            HttpResponse response = client.execute(request);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            throw e;
        }
    }



    public String submitGetRequest(String url) throws IOException {
        RequestConfig config = createRequestConfig();

        try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {
            HttpGet request = new HttpGet(this.settings.getBaseUrl()+url);
            request.setHeader("Content-Type", "application/json; charset=UTF-8");
            request.setHeader("Accept-Charset", "UTF-8");
            request.setHeader("Authorization", "Bearer "+ this.settings.getAuthToken());

            HttpResponse response = client.execute(request);
            HttpEntity httpEntity = response.getEntity();

            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            throw e; // TODO: fix exception, catch eCollect Error
        }
    }


    public String submitPostRequestMultipart(String url, HttpEntity entity) throws IOException {
        RequestConfig config = createRequestConfig();

        try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {
            HttpPost request = new HttpPost(this.settings.getBaseUrl()+url);
            request.setEntity(entity);
//            request.setHeader("Accept", "*/*");
//            request.setHeader("Accept-Encoding", "gzip, deflate");
            request.setHeader("Authorization", "Bearer "+ this.settings.getAuthToken());
            HttpResponse response = client.execute(request);
            HttpEntity respEntity = response.getEntity();
            return EntityUtils.toString(respEntity);
        } catch (IOException e) {
            throw e;
        }
    }


    public InputStream submitGetReqBinResponse(String url) throws IOException {
        RequestConfig config = createRequestConfig();


        try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {
            HttpGet request = new HttpGet(url); // note: the url in the API is passed as absolute

            request.setHeader("Content-Type", "application/json; charset=UTF-8");
            request.setHeader("Accept-Charset", "UTF-8");
            request.setHeader("Authorization", "Bearer "+ this.settings.getAuthToken());
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            InputStream stream = entity.getContent();
            return stream;
        }
    }









    public String getLimitOffsetUriString(int limit, int offset){
        String l = Integer.toString(limit);
        String o = Integer.toString(offset);
        String uri = "?size=" + l + "&from=" + o;
        return uri;
    }

    private RequestConfig createRequestConfig() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(settings.getHttpTimeout() * 1000)
                .setConnectionRequestTimeout(settings.getHttpTimeout() * 1000)
                .setSocketTimeout(settings.getHttpTimeout() * 1000)
                .build();
        return config;
    }

}
