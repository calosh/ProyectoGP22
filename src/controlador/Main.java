/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import it.uniroma1.lcl.babelnet.BabelNet;
import java.io.UnsupportedEncodingException;
import lesk.Lesk;

/**
 *
 * @author calosh
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.UnsupportedEncodingException
     */
    static public void main(String[] args) throws UnsupportedEncodingException {
        /*
        Ejecutar:
                language-identifier-server
                tokenizer-server -p 9293
                pos-tagger-server -p 9294
                kaf2json-server -p 9297
                
         */
        
        //Lesk.lesk("Estoy en el banco depositando dinero a lado del árbol", "árbol");
        
        
       // Lesk.lesk("Estoy en la Universidad recibiendo clases", "clase");
        Lesk.lesk("Estoy en la carretera en el auto esperandote", "auto");
        
        Lesk.lesk("Estoy en la Universidad recibiendo clases", "clase");         
    }
}