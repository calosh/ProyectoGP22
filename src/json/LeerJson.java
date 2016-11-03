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

import java.util.List;

import lesk.Lemma;
 
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
    public static void handleValue(com.eclipsesource.json.JsonObject.Member member, JsonValue value, List<Lemma> lemmas) {
        
        if (value.isObject()) {
            handleObject(value.asObject(), lemmas);
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
                        lemmas.add(new Lemma(pos,lemma,text,type,morphofeat));
                    }    
                }
            }
        }
    }
    
    public static void handleObject(com.eclipsesource.json.JsonObject object, List<Lemma> lemmas) {
        for (com.eclipsesource.json.JsonObject.Member next : object) {
            JsonValue value = next.getValue();
            handleValue(next, value, lemmas);
        }
    }

    public static void recurseArray(com.eclipsesource.json.JsonArray array, List<Lemma> lemmas) {
        for (JsonValue value : array) {
            handleValue(null, value, lemmas);
        }
    }
    
}