/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesk;

/**
 *
 * @author calosh
 */
public class Synset {

    private String id;
    private String word;
    private String mainGloss;
    private String mainSense;
    
    private int cont;
    
    

    public Synset() {

    }

        public Synset(String id, String word, String mainGloss, String mainSense) {
        this.id = id;
        this.mainGloss = mainGloss;
        this.mainSense = mainSense;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the mainGloss
     */
    public String getMainGloss() {
        return mainGloss;
    }

    /**
     * @param mainGloss the mainGloss to set
     */
    public void setMainGloss(String mainGloss) {
        this.mainGloss = mainGloss;
    }

    /**
     * @return the mainSense
     */
    public String getMainSense() {
        return mainSense;
    }

    /**
     * @param mainSense the mainSense to set
     */
    public void setMainSense(String mainSense) {
        this.mainSense = mainSense;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }
    
    
    @Override
    public String toString() {
        // {"pos":"V","lemma":"estoy","text":"Estoy","type":"open","morphofeat":"VMIP1S0"}
        return "id:" + id + ", mainGloss:" + mainGloss + "mainSense" + mainSense +"\n";
    }

    /**
     * @return the cont
     */
    public int getCont() {
        return cont;
    }

    /**
     * @param cont the cont to set
     */
    public void setCont(int cont) {
        this.cont = cont;
    }

}
