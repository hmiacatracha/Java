/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.exceptions;

/**
 *
 * @author hmia
 */
public class InvalidLineException extends Exception {

    public InvalidLineException() {
        super("Line not valid");
    }

    public InvalidLineException(String mensage) {
        super(mensage);
    }
}
