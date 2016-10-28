/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author calosh
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/*
    Codigo original para enviar parametros y realizar consultas mediante POST
    http://www.mysamplecode.com/2011/08/java-http-post-with-parameters-using.html
*/
public class RequestParamExample {

    private static void  HttpParameterPost() {

        HttpClient httpclient = new DefaultHttpClient();

        try {
            
            HttpPost httpPost = new HttpPost("http://localhost:8080/examples/servlets/servlet/RequestParamExample");

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("firstname","as400"));
            nameValuePairs.add(new BasicNameValuePair("lastname","samplecode"));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 

            System.out.println("executing request " + httpPost.getRequestLine());
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
                System.out.println("Chunked?: " + resEntity.isChunked());
                String responseBody = EntityUtils.toString(resEntity);
                System.out.println("Data: " + responseBody);
            }
           
           
            EntityUtils.consume(resEntity);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }
}

