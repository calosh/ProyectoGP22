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

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
import org.json.JSONObject;

import static java.nio.charset.StandardCharsets.*;

public class Service {
    
    /*
      Este metodo realiza consultas a las diferentes Web Services de OpeNER  
    */
    public static String opener(String input) throws UnsupportedEncodingException{

        input = new String(input.getBytes("UTF-8"), "ISO-8859-1");
        input = OpenerService("http://localhost:9292", input);
        System.out.println("Languaje "+input);
        input = new String(input.getBytes("UTF-8"), "ISO-8859-1");
        input = OpenerService("http://localhost:9293", input);
        System.out.println("Tokenaizer "+input);
        input = new String(input.getBytes("UTF-8"), "ISO-8859-1");
        input = OpenerService("http://localhost:9294", input);
        System.out.println("POS "+input);
        //s = OpenerService("http://localhost:9295", s);
        //System.out.println(input);
        //s = OpenerService("http://localhost:9296", s);
        //System.out.println(input);
        input = new String(input.getBytes("UTF-8"), "ISO-8859-1");
        input = OpenerService("http://localhost:9297", input);
        System.out.println(new String(input.getBytes("ISO-8859-1"), "UTF-8"));
        String inputDecode= new String(input.getBytes("ISO-8859-1"), "UTF-8");
        
        try {
            // http://stackoverflow.com/questions/5245840/how-to-convert-string-to-jsonobject-in-java
            JSONObject jsonObj = new JSONObject(inputDecode);
            //System.out.println(jsonObj);
            JSONObject lemma = jsonObj.getJSONObject("terms");

            //System.out.println("ESte es el lemma" + lemma);
            return lemma+"";

        } catch (Exception e) {
            System.out.println("Error json");
            return null;
        }
    }
   /*
    Metodo que permite enviar y recibir parametros mediante POST.
    Devuelve una cadena de la consulta a la Web Service
    http://www.mysamplecode.com/2011/08/java-http-post-with-parameters-using.html
    */
    private static String  OpenerService(String url, String input) {  
        
        HttpClient httpclient = new DefaultHttpClient();
        
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("input",input));
            if("http://localhost:9292".equals(url)){
                System.out.println("Siíií");
            }else{
                 nameValuePairs.add(new BasicNameValuePair("kaf","true"));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
            //System.out.println("executing request " + httpPost.getRequestLine());
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            //System.out.println("----------------------------------------");
            //System.out.println(response.getStatusLine());
            if (resEntity != null) {
                //System.out.println("Response content length: " + resEntity.getContentLength());
                //System.out.println("Chunked?: " + resEntity.isChunked());
                String responseBody = EntityUtils.toString(resEntity);
                //System.out.println("Data: " + responseBody);
                return responseBody;
            }
            EntityUtils.consume(resEntity);
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return null;
    }
}