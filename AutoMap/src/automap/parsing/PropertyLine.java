package automap.parsing;

import automap.exceptions.InvalidLineException;

/**
 *
 * @author hmia
 */
public class PropertyLine extends Line {

    private String modifier;
    private final String javaType;
    private final String name;

    public PropertyLine(String line) throws InvalidLineException {
        super(line);
        if (!isValid()) {
            throw new InvalidLineException("Not valid property");
        }
        this.modifier = getToken(0).toLowerCase();
        this.javaType = getToken(1);
        this.name = getToken(2).replaceAll(";", "");
    }

    private boolean isValid() {
        return size() == 3 && getToken(0).equalsIgnoreCase("private");
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
