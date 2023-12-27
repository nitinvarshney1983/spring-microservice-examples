package com.techwhisky.book.details.configuration;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfiguration {

    private static final Logger LOGGER= LoggerFactory.getLogger(AppConfiguration.class);

    @Value("${rest.client.connectionTimeout}")
    private int connectionTimeout;

    @Value("${rest.client.readTimeout}")
    private int readTimeout;

    @Value("${rest.client.connection.pool.maxIdleConnection}")
    private int maxIdleConnection;

    @Value("${rest.client.connection.pool.keepAliveDuration}")
    private int keepAliveDuration;

    @Value("${rest.client.connection.maxRetryCount}")
    private int maxRetryCount;

    @Value("${rest.client.connection.retryAfterMilliseconds}")
    private int retryAfterMilliseconds;

    @Value("${rest.client.sslVerification}")
    private boolean sslVerification;

    /*
        An RestTemplate Can be created in multiple ways
     */

    @Bean
    @LoadBalanced
    public RestTemplate createRestTemplate(RestTemplateBuilder restTemplateBuilder) throws NoSuchAlgorithmException, KeyManagementException {
        RestTemplate restTemplate = new RestTemplate();

        // create the okhttp client builder
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        ConnectionPool okHttpConnectionPool = new ConnectionPool(maxIdleConnection, keepAliveDuration, TimeUnit.SECONDS);
        builder.connectionPool(okHttpConnectionPool);
        builder.connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS);
        builder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
        builder.retryOnConnectionFailure(false);
        //Adding custom retry
        if(maxRetryCount>0){
            customRetryHandler(builder, maxRetryCount, retryAfterMilliseconds);
        }

        if(!sslVerification){
            setupBuilderForIgnoringCerts(builder);
        }
        // embed the created okhttp client to a spring rest template
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory(builder.build()));
        return restTemplate;
    }

    private void customRetryHandler(OkHttpClient.Builder builder, int retryCount, int retryAfterMilliseconds) {
        builder.addInterceptor(new Interceptor() {
            int retriedCount = 0;

            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request();

                Response response = chain.proceed(request);
                while ((!response.isSuccessful() || response.body().contentLength()==-1) && retriedCount < maxRetryCount) {
                    try {
                        response.close();
                        TimeUnit.MILLISECONDS.sleep(retryAfterMilliseconds);
                        LOGGER.info("Retried count value is--------------->"+retriedCount);
                        retriedCount++;
                        response=chain.proceed(request);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return response;
            }
        });
    }

    /*
        This is for ignoring cert. For POC purpose we are not handling certs but in case of secure call, you may need to check certs
        present in trust store. Need to write another method to load those certs.
        will add method later
     */
    private void setupBuilderForIgnoringCerts(OkHttpClient.Builder builder) throws NoSuchAlgorithmException, KeyManagementException {

        final TrustManager[] trustManagers=new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };
        final SSLContext sslContext=SSLContext.getInstance("SSL");
        sslContext.init(null,trustManagers, new SecureRandom());
        SSLSocketFactory sslSocketFactory=sslContext.getSocketFactory();
        builder.sslSocketFactory(sslSocketFactory,(X509TrustManager) trustManagers[0]);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
    }
}
