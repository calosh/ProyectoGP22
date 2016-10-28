/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static json.LeerJson.handleObject;

import service.Service;

import com.eclipsesource.json.JsonObject;

/**
 *
 * @author calosh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static public void main(String[] args) {
                
        /*
        String data = Service.opener("Estoy en el banco depositando dinero");
        System.out.println(data);
        JsonObject jsonObject = JsonObject.readFrom(data);
        handleObject(jsonObject);
        */
        
        /*
        String data = Service.opener("Estoy en el banco depositando dinero");
        System.out.println(data);

        JsonParser parser = new JsonParser();
        JsonElement datos = parser.parse(data);
        dumpJSONElement(datos);
        */
        
        String data = Service.opener("Estoy depositando dinero en el banco de Loja");
        System.out.println(data);
        JsonObject jsonObject = JsonObject.readFrom(data);
        handleObject(jsonObject);
        
        //System.out.println(json.LeerJson.lemmas);
       
        json.LeerJson.lemmas.
                stream().
                forEach(System.out::println);
               
    }
}

