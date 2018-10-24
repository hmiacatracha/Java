package automap.parsing;

import automap.exceptions.InvalidLineException;

/**
 *
 * @author hmia
 */
public class PrimaryKeyLine extends Line {

    public PrimaryKeyLine(String line) throws InvalidLineException {
        super(line);
        if (!isValid()) {
            throw new InvalidLineException("Not valid primary key line");
        }
    }

    private boolean isValid() {
        return super.size() == 3 && getToken(0).equalsIgnoreCase("private")
                && getToken(1).equalsIgnoreCase("Long")
                && getToken(2).equalsIgnoreCase("Id");
    }
    
}
