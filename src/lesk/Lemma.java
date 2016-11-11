/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author calosh
 */

/*
    Esta clase se utiliza para guardar informacion de cada palabra 
*/
public class Lemma {
    // Conjunto de Lemmas de la frase
    public static List<Lemma> lemmas = new ArrayList<>();
    // Conjunto de Lemmas de la frase sin Stop Words
    
    private String pos;
    private String lemma;
    private String text;
    private String type;
    private String morphofeat;

    public Lemma() {
    }
    
    public Lemma(String pos,String lemma,String text,String type,String morphofeat){
        this.pos=pos;
        this.lemma=lemma;
        this.text=text;
        this.type=type;
        this.morphofeat=morphofeat;
    }

    /**
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * @return the lemma
     */
    public String getLemma() {
        return lemma;
    }

    /**
     * @param lemma the lemma to set
     */
    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the morphofeat
     */
    public String getMorphofeat() {
        return morphofeat;
    }

    /**
     * @param morphofeat the morphofeat to set
     */
    public void setMorphofeat(String morphofeat) {
        this.morphofeat = morphofeat;
    }
    
    @Override
    public String toString(){
        // {"pos":"V","lemma":"estoy","text":"Estoy","type":"open","morphofeat":"VMIP1S0"}
        return "pos:"+pos+", lemma:"+lemma+", text: "+text+", type:"+type+", morphofeat:"+morphofeat+"\n";
    }
    
    //public static void deleteStopWords(List<Lemma> lemmas, List<Lemma> lemmasFiltrados){
    public static void deleteStopWords(List<Lemma> lemmas){
        // verbos, adjetivos o sustantivos.
        Iterator<Lemma> it = lemmas.iterator();
        
        while(it.hasNext()){
            Lemma l =it.next();
            String pos = l.getPos();
            if(pos.equals("N")||pos.equals("V")||pos.equals("G")){
                /*
                if(l.getLemma().equals("ser")){
                    it.remove();
                }
                */
                
            }else{
                it.remove();
            } 
        }    
    }

}