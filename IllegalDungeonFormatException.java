/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *
 * @author Meredith
 */
public class IllegalDungeonFormatException extends Exception {
    public IllegalDungeonFormatException(String errorMessage){
        System.out.println(errorMessage);
    }
}

