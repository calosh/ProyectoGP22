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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;

import lesk.Lemma;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import static prueba.Json.PRETTY_PRINT_INDENT_FACTOR;
import static prueba.Json.TEST_XML_STRING;
 
public class LeerJson {
    
    public static int cont = 0;
    static String pos = "";
    static String lemma = "";
    static String text = "";
    static String type = "";
    static String morphofeat = "";

 
    /*
        Metodo que recorre un json y almacena la informacion en objetos de la clase Lemma
        Metodo original: http://stackoverflow.com/questions/22111857/parse-recursively-unknown-json-input-structure-in-java
    */
    public static void handleValue(com.eclipsesource.json.JsonObject.Member member, JsonValue value) {
        
        if (value.isObject()) {
            handleObject(value.asObject());
        } else if (value.isString()) {
            if (member != null) {
                if(cont<=5){
                     if (member.getName().equals("pos")) {
                         pos=value.asString();
                    }if (member.getName().equals("lemma")) {
                         lemma=value.asString();
                    }if (member.getName().equals("text")) {
                         text=value.asString();
                    }if (member.getName().equals("type")) {
                         type=value.asString();
                    }if (member.getName().equals("morphofeat")) {
                         morphofeat=value.asString();
                    }
                    
                    cont++;
                       
                    if (cont==5) {
                        cont=0;
                        Lemma.lemmas.add(new Lemma(pos,lemma,text,type,morphofeat));
                    }    
                }
            }
        }
    }
    
    public static void handleObject(com.eclipsesource.json.JsonObject object) {
        for (com.eclipsesource.json.JsonObject.Member next : object) {
            JsonValue value = next.getValue();
            handleValue(next, value);
        }
    }

    public static void recurseArray(com.eclipsesource.json.JsonArray array) {
        for (JsonValue value : array) {
            handleValue(null, value);
        }
    }
    
     /*
    public static void handleValue(com.eclipsesource.json.JsonObject.Member member, JsonValue value) {
        // http://stackoverflow.com/questions/22111857/parse-recursively-unknown-json-input-structure-in-java
        if (value.isArray()) {
            if (member != null) {
                System.out.print("nama = " + member.getName());
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
                System.out.print("nami = " + member.getName());
            }
            System.out.println(", null value");
        } else if (value.isNumber()) {
            if (member != null) {
                System.out.print("namo = " + member.getName());
            }
            System.out.println(", number value = " + value.asDouble());
        } else if (value.isObject()) {
            if (member != null) {
                System.out.print("namu = " + member.getName());
            }
            System.out.println(", object value ");
            handleObject(value.asObject());
        } else if (value.isString()) {
            if (member != null) {
                System.out.print("namp = " + member.getName());
            }
            System.out.println(", string value = " + value.asString());
        }
    }
    */
    
    /*
    Metodo para recorrer json
    No utilizado
    http://blog.openalfa.com/como-leer-y-escribir-ficheros-json-en-java
    */
    public static void dumpJSONElement(JsonElement elemento) {

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
}