package lesk;

import com.eclipsesource.json.JsonObject;
import it.uniroma1.lcl.babelnet.BabelImage;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelNetUtils;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.BabelSenseComparator;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetComparator;
import it.uniroma1.lcl.babelnet.BabelSynsetID;
import it.uniroma1.lcl.babelnet.BabelSynsetIDRelation;
import it.uniroma1.lcl.babelnet.InvalidBabelSynsetIDException;
import it.uniroma1.lcl.babelnet.data.BabelGloss;
import it.uniroma1.lcl.babelnet.data.BabelPOS;
import it.uniroma1.lcl.babelnet.data.BabelSenseSource;
import it.uniroma1.lcl.jlt.util.Language;
import it.uniroma1.lcl.jlt.util.ScoredItem;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Multimap;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import static javafx.scene.input.KeyCode.T;
import static json.LeerJson.handleObject;
import static lesk.Lemma.deleteStopWords;
import static lesk.Lemma.lemmas;
import service.Service;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A demo class to test {@link BabelNet}'s various features.
 *
 * @author cecconi, navigli, vannella
 */
public class Lesk {
    
    // http://www.bdigital.unal.edu.co/48257/1/1037609063.2015.pdf
    
    /*
    public static void lesk(String frase, String desambiguar) throws UnsupportedEncodingException{
        List<Synset> mejorR = new ArrayList<>();
        int mayor = 0;
        Synset bestSense = new Synset();
        // Devueleve JSON de frase
        JsonObject jsonObject = Service.opener(frase);
        // Crea un ArrayList Tipo Lemma con todas las palabras
        handleObject(jsonObject, lemmas);
        // Elimina los stop words
        Lemma.deleteStopWords(lemmas, lemmasContexto);
        // Elimina palabra a desambiguar
        Lesk.contexto(Lemma.lemmasContexto, desambiguar);
        
        // Recorrer lemmasContexto
        Iterator<Lemma> it = lemmasContexto.iterator();
        while (it.hasNext()) {
            System.out.println("Frase: "+frase);
            Lemma l = it.next();
            String text = l.getText();
            System.out.println("Palabra: "+text);
            // Obtienes los sentidos del contexto
            List<Synset> sentidosPalabra = sentidosBabelNet(text); 
            System.out.println("Sentidos: "+sentidosPalabra);
            // Se recorre cada sentido
            List<Lemma> lemmasContextoPalabras = new ArrayList<>();
            List<Lemma> lemmasContextoPalabrasF = new ArrayList<>();
            Iterator<Synset> itSentidos = sentidosPalabra.iterator();

            while (itSentidos.hasNext()) {
                Synset s = itSentidos.next();
                jsonObject = Service.opener(s.getMainSense()+". "+s.getMainGloss());
                handleObject(jsonObject, lemmasContextoPalabras);
                // Elimina los stop words --> 
                Lemma.deleteStopWords(lemmasContextoPalabras, lemmasContextoPalabrasF);
                // Elimina palabra a desambiguar
                Lesk.contexto(lemmasContextoPalabrasF, "");
                
                System.out.println("Lemas Contexto: "+lemmasContexto);
                System.out.println("LemasCPF: "+lemmasContextoPalabrasF);
                mejorR = intersection(lemmasContexto, lemmasContextoPalabrasF, s);
                System.out.println("Inters: "+mejorR);
                
                Iterator<Synset> bs = mejorR.iterator();
                while (bs.hasNext()) {
                    System.out.println("Frase: " + frase);
                    Synset syn = bs.next();
                    if (syn.getCont() > mayor) {
                        mayor = syn.getCont();
                        bestSense = syn;
                    }
                }         
                // Limpiar datos 
                lemmasContextoPalabras.clear();
                lemmasContextoPalabrasF.clear();
            } 
        }    
        System.out.println("El mayor es: "+mayor+" "+bestSense);
    }
    */
    public static void lesk(String frase, String desambiguar) throws UnsupportedEncodingException{

        System.out.println("Palabra: "+listLemmas(desambiguar));
        System.out.println("Contexto: "+matrizLemmas(frase, desambiguar, lemmas));
    }
    
    public static List<Object> listLemmas(String palabraDesambiguar) throws UnsupportedEncodingException{
        List<Object> lista = new ArrayList<>();
        List<Synset> sentidosPalabra = sentidosBabelNet(palabraDesambiguar);
        
        // Se recorre cada sentido
        Iterator<Synset> itSentidos = sentidosPalabra.iterator();

        while (itSentidos.hasNext()) {
            List<Lemma> lemmasContextoPalabras = new ArrayList<>();

            Synset s = itSentidos.next();
            lista.add(s);
            JsonObject jsonObject = Service.opener(s.getMainSense() + ". " + s.getMainGloss());
            handleObject(jsonObject, lemmasContextoPalabras);
            // Elimina los stop words --> 
            Lemma.deleteStopWords(lemmasContextoPalabras);
            // Elimina palabra a desambiguar
            //Lesk.contexto(lemmasContextoPalabras, "");
            lista.add(lemmasContextoPalabras);
        }
        return lista;
    }
    
    public static List<Object> matrizLemmas(String frase,String desambiguar, List<Lemma> lemmas) throws UnsupportedEncodingException{
        // Devueleve JSON de frase
        JsonObject jsonObject = Service.opener(frase);
        // Crea un ArrayList Tipo Lemma con todas las palabras
        handleObject(jsonObject, lemmas);
        // Elimina los stop words
        Lemma.deleteStopWords(lemmas);
        // Elimina palabra a desambiguar
        Lesk.contexto(lemmas, desambiguar);

        // Itero sobres los lemas del contexto

        List<Object> lista = new ArrayList<>();

        Iterator<Lemma> it = lemmas.iterator();
        while (it.hasNext()) {
            Lemma l = it.next();
            String text = l.getText();

            List<Synset> sentidosPalabra = sentidosBabelNet(text);
            // Se recorre cada sentido
            
            Iterator<Synset> itSentidos = sentidosPalabra.iterator();

            while (itSentidos.hasNext()) {
                List<Lemma> lemmasContextoPalabras = new ArrayList<>();
                
                Synset s = itSentidos.next();
                //lista.add(s);
                jsonObject = Service.opener(s.getMainSense() + ". " + s.getMainGloss());
                handleObject(jsonObject, lemmasContextoPalabras);
                // Elimina los stop words --> 
                Lemma.deleteStopWords(lemmasContextoPalabras);
                // Elimina palabra a desambiguar
                //Lesk.contexto(lemmasContextoPalabras, "");
                lista.add(lemmasContextoPalabras);
            }
        }

        return lista;
    }
    
    /*
        Lemmas Filtrados sin palabra a desambiguar
    */
    public static void contexto( List<Lemma> lemmas,String desambiguar){
        Iterator<Lemma> it = lemmas.iterator();
        while (it.hasNext()) {
            Lemma l = it.next();
            String text = l.getText();
            
            if (text.equals(desambiguar)) {
                it.remove();
            }
        }        
    }
    
    public static List<Synset> sentidosBabelNet(String palabra) throws UnsupportedEncodingException{
        //System.out.println("Sentido: "+palabra);
        List<Synset> sentidos = new ArrayList<>();
        BabelNet bn = BabelNet.getInstance();
        palabra = new String(palabra.getBytes("UTF-8"), "ISO-8859-1");
        for (BabelSynset synset : bn.getSynsets(palabra, Language.ES)) {
            //System.out.println("Synset ID: " + synset.getId());
            //System.out.println("=== DEMO ===");
            //System.out.println("Welcome on " + synset.getMainSense(Language.ES).getLemma().replace("_", " "));
            //System.out.println(synset.getMainGloss(Language.ES).getGloss());
            String palabra2= new String(palabra.getBytes("ISO-8859-1"), "UTF-8");
            sentidos.add(new Synset(synset.getId()+"",palabra2, synset.getMainGloss(Language.ES).getGloss(),synset.getMainSense(Language.ES).getLemma().replace("_", " ")));
        }
        return sentidos;
    }
    

    public static List<Synset> intersection(List<Lemma> lemmas,List<Lemma> lemmas2, Synset s) {
        List<Synset> list = new ArrayList<>();
        Iterator<Lemma> it = lemmas.iterator();
        while (it.hasNext()) {
            Lemma l = it.next();
            
            Iterator<Lemma> it2 = lemmas2.iterator();
            while (it2.hasNext()) {
                Lemma l2 = it2.next();
            
                if(l.getLemma().equals(l2.getLemma())) {
                    s.setCont(s.getCont()+1);
                    list.add(s);
                }
            }
        }
       return list; 
    }
    
}
