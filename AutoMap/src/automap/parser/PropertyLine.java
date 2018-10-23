package automap.parser;

/**
 *
 * @author hmia
 */
public class PropertyLine extends Line {

    private String modifier;
    private final String javaType;
    private final String name;

    public PropertyLine(String line) throws NoValidLineException {
        super(line);
        if (!isValid()) {
            throw new NoValidLineException("Not valid property");
        }
        this.modifier = super.getToken(0).toLowerCase();
        this.javaType = super.getToken(1);
        this.name = super.getToken(2).replaceAll(";", "");
    }

    private boolean isValid() {
        return super.size() == 3 && super.getToken(0).equalsIgnoreCase("private");
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
