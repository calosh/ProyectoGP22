package json;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author calosh
 */
import com.eclipsesource.json.JsonValue;
import java.io.FileReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import java.util.Map.Entry;
 

import java.io.FileReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import java.util.Map.Entry;
import service.Service;
 
public class LeerJson {
    
    public static void dumpJSONElement(JsonElement elemento) {
        // http://blog.openalfa.com/como-leer-y-escribir-ficheros-json-en-java

        if (elemento.isJsonObject()) {
            System.out.println("Es objeto");
            JsonObject obj = elemento.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String, JsonElement> entrada = iter.next();
                System.out.println("Clave: " + entrada.getKey());
                System.out.println("Valor:");
                dumpJSONElement(entrada.getValue());
            }

        } else if (elemento.isJsonArray()) {
            JsonArray array = elemento.getAsJsonArray();
            System.out.println("Es array. Numero de elementos: " + array.size());
            java.util.Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                JsonElement entrada = iter.next();
                dumpJSONElement(entrada);
            }
        } else if (elemento.isJsonPrimitive()) {
            System.out.println("Es primitiva");
            JsonPrimitive valor = elemento.getAsJsonPrimitive();
            if (valor.isBoolean()) {
                System.out.println("Es booleano: " + valor.getAsBoolean());
            } else if (valor.isNumber()) {
                System.out.println("Es numero: " + valor.getAsNumber());
            } else if (valor.isString()) {
                System.out.println("Es texto: " + valor.getAsString());
            }
        } else if (elemento.isJsonNull()) {
            System.out.println("Es NULL");
        } else {
            System.out.println("Es otra cosa");
        }
    }
    
    
    public static void handleValue(com.eclipsesource.json.JsonObject.Member member, JsonValue value) {
        // http://stackoverflow.com/questions/22111857/parse-recursively-unknown-json-input-structure-in-java
        if (value.isArray()) {
            if (member != null) {
                System.out.print("name = " + member.getName());
            }
            System.out.println("array value ");
            recurseArray(value.asArray());
        } else if (value.isBoolean()) {
            if (member != null) {
                System.out.print("name = " + member.getName());
            }
            System.out.println(", boolean value = " + value.asBoolean());
        } else if (value.isNull()) {
            if (member != null) {
                System.out.print("name = " + member.getName());
            }
            System.out.println(", null value");
        } else if (value.isNumber()) {
            if (member != null) {
                System.out.print("name = " + member.getName());
            }
            System.out.println(", number value = " + value.asDouble());
        } else if (value.isObject()) {
            if (member != null) {
                System.out.print("name = " + member.getName());
            }
            System.out.println(", object value ");
            handleObject(value.asObject());
        } else if (value.isString()) {
            if (member != null) {
                System.out.print("name = " + member.getName());
            }
            System.out.println(", string value = " + value.asString());
        }
    }

    private static void handleObject(com.eclipsesource.json.JsonObject object) {
        for (com.eclipsesource.json.JsonObject.Member next : object) {
            JsonValue value = next.getValue();
            handleValue(next, value);
        }
    }

    private static void recurseArray(com.eclipsesource.json.JsonArray array) {
        for (JsonValue value : array) {
            handleValue(null, value);
        }
    }
    
    
    
    
    /*
    public static void main(String args[]) throws java.io.IOException {
        String data = Service.opener("Estoy en el banco depositando dinero");
        System.out.println(data);
        
        
        JsonParser parser = new JsonParser();
        JsonElement datos = parser.parse(data);
        dumpJSONElement(datos);
    }
    */
}