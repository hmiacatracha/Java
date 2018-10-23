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
public class NoPersistenteClassException extends Exception {

    public NoPersistenteClassException() {
        super("The class insn't persistente class");
    }

    public NoPersistenteClassException(String className) {
        super("The class " + className + " isn't a persistente class");
    }
}
