/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.parsing;

import automap.exceptions.InvalidLineException;

/**
 *
 * @author hmia
 */
public class ManyToManyRelationLine extends PropertyLine {

    public ManyToManyRelationLine(String line) throws InvalidLineException {
        super(line);
    }
}
