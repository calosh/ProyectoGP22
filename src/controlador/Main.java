/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import static json.LeerJson.dumpJSONElement;
import org.json.JSONObject;
import service.Service;


import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
/**
 *
 * @author calosh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static public void main(String[] args) {
        
    //    JSONObject data = new JSONObject();
        /*
        String data = Service.opener("Estoy en el banco depositando dinero");
        System.out.println(data);
        JsonObject jsonObject = JsonObject.readFrom(data);
        handleObject(jsonObject);
    
        */
        
        String data = Service.opener("Estoy en el banco depositando dinero");
        System.out.println(data);

        JsonParser parser = new JsonParser();
        JsonElement datos = parser.parse(data);
        dumpJSONElement(datos);
    }
    
}


