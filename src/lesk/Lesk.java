package lesk;

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
import java.util.ArrayList;
import java.util.Iterator;
import static lesk.Lemma.lemmas;
import static lesk.Lemma.lemmasFiltrados;

/**
 * A demo class to test {@link BabelNet}'s various features.
 *
 * @author cecconi, navigli, vannella
 */
public class Lesk {
    
    // http://www.bdigital.unal.edu.co/48257/1/1037609063.2015.pdf
    
    
    public static void buscarSentido(String desambiguar){
        BabelSense bestSense=null;
        int max_counter = 0;
        Lemma.deleteStopWords();
        
        // Contexto sin palabra a desambiguar
        List<Lemma> context = new ArrayList<>();
        Iterator<Lemma> it = lemmasFiltrados.iterator();
        while (it.hasNext()) {
            Lemma l = it.next();
            String pos = l.getText();
            if (pos.equals(desambiguar)) {
                
            }else{
                context.add(new Lemma(l.getPos(), l.getLemma(), l.getText(), l.getType(), l.getMorphofeat()));
            }
        }
        
        System.out.println(context);
    }
    
    public static void sentidosBabelNet(){
        BabelNet bn = BabelNet.getInstance();
        for (BabelSynset synset : bn.getSynsets("arbol", Language.ES)) {
            System.out.println("Synset ID: " + synset.getId());
            
            System.out.println("=== DEMO ===");
            System.out.println("Welcome on " + synset.getMainSense(Language.ES).getLemma().replace("_", " "));
            System.out.println(synset.getMainGloss(Language.ES).getGloss());
            
        }
        
    }
    
}
