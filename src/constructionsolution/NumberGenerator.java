/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constructionsolution;

import org.hibernate.Session;

/**
 *
 * @author Shashiprabha
 */
public class NumberGenerator {
    
        public String getGenaretedNumber(String prefix, long primaryKey) throws Exception {    

        String fformattedPK = String.format("%05d", (primaryKey));
        String genNo = prefix +  "_" + fformattedPK;
        return genNo;
    }
    
        
}
