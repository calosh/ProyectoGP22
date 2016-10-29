/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static json.LeerJson.handleObject;

import service.Service;

import com.eclipsesource.json.JsonObject;
import lesk.Lemma;

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
        
        /*
        Ejecutar:
                tokenizer-server -p 9293
                pos-tagger-server -p 9294
                kaf2json-server -p 9297
                
         */
        String data = Service.opener("Estoy depositando dinero en el banco, cuando termire voy a verte en la casa. Estarasme esperando en la puerta de tu casa");
        //System.out.println(data);
        JsonObject jsonObject = JsonObject.readFrom(data);
        handleObject(jsonObject);
       
        /*
        System.out.println(lesk.Lemma.lemmas);
        esk.Lemma.lemmas.
                stream().
                forEach(System.out::println);
        */
        
        Lemma.filtrarLemmas();
        System.out.println(lesk.Lemma.lemmas);
        System.out.println(lesk.Lemma.lemmasFiltrados);
        
               
    }
}

