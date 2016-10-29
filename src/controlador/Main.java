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
                tokenizer-server -p 9293
                tokenizer-server -p 9293
                kaf2json-server -p 9297
                
         */
        
        // Codificaion
        String frase = "Los conos de pino cuelgan en un árbol, mi ñaño se llama luis";
        
        System.out.println(frase);

        String data = Service.opener(frase);
        JsonObject jsonObject = JsonObject.readFrom(data);
        handleObject(jsonObject);
        
        System.out.println(Lemma.lemmas);

        
        Lesk.buscarSentido("banco");
        Lesk.sentidosBabelNet();
               
    }
}

