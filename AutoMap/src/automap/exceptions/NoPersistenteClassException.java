/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.exceptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author hmia
 */
public class NoPersistenteClassException extends Exception {

    private HashSet<String> messages = new HashSet<>();

    public NoPersistenteClassException() {
        super("The class insn't persistente class");
    }

    public NoPersistenteClassException(String className) {
        super("The class " + className + " isn't a persistente class");
    }

    public NoPersistenteClassException(String className, HashSet<String> messages) {
        super("The class " + className + " isn't a persistente class due to:\n" + messages);
        this.messages = messages;
    }

    public HashSet<String> getMessages() {
        return messages;
    }

    public boolean hasMessages() {
        return messages.size() > 0;
    }
}
