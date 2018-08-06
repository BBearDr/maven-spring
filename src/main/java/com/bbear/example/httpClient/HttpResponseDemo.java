package com.bbear.example.httpClient;

import org.apache.http.*;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;

/**
 * @author junxiongchen
 * @date 2018/08/03
 */
public class HttpResponseDemo {


    public static void main(String[] args) {
        HttpResponseDemo httpResponseDemo = new HttpResponseDemo();
        httpResponseDemo.httpElementDemo();
    }
    private static HttpResponse getResponse() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie", "c1=b;  path=\"/\", c3=c;  domain=localhost");
        return response;
    }

    /**
     * 获得每个消息的消息头
     */
    private void httpDemo(){
        HeaderIterator headerIterator = getResponse().headerIterator("Set-Cookie");
        while (headerIterator.hasNext()) {
            System.out.println(headerIterator.next());
        }
    }

    /**
     * 获得消息头内的元素
     */
    private void httpElementDemo(){
        HeaderElementIterator it = new BasicHeaderElementIterator(getResponse().headerIterator());
        while (it.hasNext()) {
            HeaderElement headerElement = it.nextElement();
            System.out.println(headerElement.getName() + "----" + headerElement.getValue());
            NameValuePair[] parameters = headerElement.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                System.out.println(parameters[i]);
            }
        }
    }

}
