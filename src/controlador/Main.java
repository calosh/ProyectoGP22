/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static json.LeerJson.handleObject;

import service.Service;

import com.eclipsesource.json.JsonObject;
import java.io.UnsupportedEncodingException;
import lesk.Lemma;
import lesk.Lesk;
import static lesk.Lesk.sentidosBabelNet;

/**
 *
 * @author calosh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static public void main(String[] args) throws UnsupportedEncodingException {
        /*
        Ejecutar:
                language-identifier-server
                tokenizer-server -p 9293
                pos-tagger-server -p 9294
                kaf2json-server -p 9297
                
         */
        
        // Codificaion
        
        //Lesk.lesk("Estoy en el banco depositando dinero a lado del árbol", "árbol");
        Lesk.lesk("Estoy en el banco depositando dinero", "banco");
        
        //System.out.println("Lemmas filtrados: "+Lemma.lemmasContexto);
        //System.out.println(sentidosBabelNet("banco"));  
    }
}