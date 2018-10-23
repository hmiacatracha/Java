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
public class PackLine extends Line {

    private final String name;

    public PackLine(String line) throws NoValidLineException {
        super(line);
        if (!isValid(line)) {
            throw new NoValidLineException("This line isn't a package line");
        }
        this.name = super.getToken(1).replaceAll(";", "").trim();
    }

    private boolean isValid(String line) {
        if (super.size() != 2) {
            return false;
        }
        if (super.getToken(0).startsWith("package")) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
