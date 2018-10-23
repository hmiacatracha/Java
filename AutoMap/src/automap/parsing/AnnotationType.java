/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.parsing;

/**
 *
 * @author hmia
 */
public enum AnnotationType {

    H("//H"), HPK("//HPK"), HColect("//HColect"), HP11("//H11"), HM1("//HPM1"), H1M("//H1M"), HMM("//HMM");

    private String name = null;

    private AnnotationType(String desc) {
        this.name = desc;
    }

    public String getName() {
        return name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

}
