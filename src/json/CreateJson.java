/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import static prueba.Json.PRETTY_PRINT_INDENT_FACTOR;
import static prueba.Json.TEST_XML_STRING;

/**
 *
 * @author calosh
 */

/*No se usa*/
public class CreateJson {
    
    private static int PRETTY_PRINT_INDENT_FACTOR = 4;
    
    public static String crearJson(String TEST_XML_STRING) {
        String jsonPrettyPrintString="";
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
            jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(jsonPrettyPrintString);
            
            // http://stackoverflow.com/questions/5245840/how-to-convert-string-to-jsonobject-in-java
            JSONObject jsonObj = new JSONObject(jsonPrettyPrintString);
            //System.out.println(jsonObj);
            JSONObject lemma = jsonObj.getJSONObject("terms");

            //System.out.println("ESte es el lemma" + lemma);
            return lemma + "";
            
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
        
        return jsonPrettyPrintString;
    }
    
}
