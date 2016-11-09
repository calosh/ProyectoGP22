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



import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import static json.LeerJson.handleObject;
import service.Service;

import java.util.ArrayList;
import java.util.List;
import static lesk.Lemma.lemmas;

/**
 * A demo class to test {@link BabelNet}'s various features.
 *
 * @author cecconi, navigli, vannella
 */
public class Lesk {
    
    public static BabelNet bn = BabelNet.getInstance();
    
    // http://www.bdigital.unal.edu.co/48257/1/1037609063.2015.pdf
    
    public static void lesk(String frase, String desambiguar) throws UnsupportedEncodingException {

        List<Object> listaLemmasDesambiguar = listLemmas(desambiguar);
        List<Object> matrizLemmasContexto = matrizLemmas(frase, desambiguar, lemmas);

        //System.out.println("Palabra: " + listaLemmasDesambiguar);
        //System.out.println("Contexto: " + matrizLemmasContexto);

        int cont = 0;
        int contP = 0;
        Synset bestSense = new Synset();
        Synset auxBestSense = new Synset();
        Iterator<Object> itSentidos = listaLemmasDesambiguar.iterator();
        while (itSentidos.hasNext()) {
            Object obj = itSentidos.next();

            if (cont == 0) {
                auxBestSense = (Synset) obj;
                System.out.println("Synset: " + auxBestSense);
                cont++;
            } else {
                List<Lemma> l = (List<Lemma>) obj;
                Iterator<Lemma> it2 = l.iterator();
                while (it2.hasNext()) {
                    Lemma lem = it2.next();
                    
                    // Recorrer Matriz
                    Iterator<Object> it3 = matrizLemmasContexto.iterator();
                    while (it3.hasNext()) {
                        List<Lemma> lem2 = (List<Lemma>) it3.next();

                        Iterator<Lemma> it4 = lem2.iterator();
                        while (it4.hasNext()) {
                            Lemma lem3 = it4.next();
                            if (lem.getLemma().equals(lem3.getLemma())) {
                                contP++;
                                System.out.println(lem.getText());
                                System.out.println(contP);
                            }

                        }/*
                        if (contP > bestSense.getCont()) {
                            bestSense = auxBestSense;
                            bestSense.setCont(contP);
                            contP = 0;
                            System.out.println("Mejor hasta ahora: " + bestSense);
                        } else {
                            contP = 0;
                        }    
                         */
                    }
                    /*
                    if (contP > bestSense.getCont()) {
                        bestSense = auxBestSense;
                        bestSense.setCont(contP);
                        contP = 0;
                        System.out.println("Mejor hasta ahora: " + bestSense);
                    } else {
                        contP = 0;
                    }
                     */
                }

                if (contP > bestSense.getCont()) {
                    bestSense = auxBestSense;
                    bestSense.setCont(contP);
                    contP = 0;
                    System.out.println("Mejor hasta ahora: " + bestSense);
                } else {
                    contP = 0;
                }
                //
                cont = 0;

            }

        }
        System.out.println("Mejor sentido " + bestSense + " cont: " + bestSense.getCont());
        
        clearData();
    }
    
    /*
        Retorna un arratList con la lista de 
    */
    public static List<Object> listLemmas(String palabraDesambiguar) throws UnsupportedEncodingException {
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
            // Elimina palabra a desambiguar2
            //Lesk.contexto(lemmasContextoPalabras, "");
            lista.add(lemmasContextoPalabras);
        }
        
        return lista;
        
    }

    public static List<Object> matrizLemmas(String frase, String desambiguar, List<Lemma> lemmas) throws UnsupportedEncodingException {
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
    
    /*
        Retorna un Array List<Synset> con el conjunto de definiciones de una palabra
    */
    public static List<Synset> sentidosBabelNet(String palabra) throws UnsupportedEncodingException{
        //System.out.println("Sentido: "+palabra);
        List<Synset> sentidos = new ArrayList<>();
        /*
        List<Synset> sentidos = new ArrayList<>();
        BabelNet bn = BabelNet.getInstance();
        */
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

    private static void clearData() {
        lemmas=new ArrayList<>();
        json.LeerJson.cont=0;
        
    }
    
}
