/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.parser;

/**
 *
 * @author hmia
 */
public class MethodLine extends Line {

    private String modifier;
    private String returnType;
    private String name;
    private String[] params;
    
    public MethodLine(String line) {
        super(line);
    }

    
}
