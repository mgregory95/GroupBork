/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;


public class IllegalSaveFormatException extends Exception {
    public IllegalSaveFormatException(String errorMessage){
        System.out.println(errorMessage);
    }
}
