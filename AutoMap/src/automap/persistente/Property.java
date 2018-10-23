/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.persistente;

/**
 *
 * @author hmia
 */
public class Property {

    private final String name;
    private final String javaType;

    Property(String name, String javaType) {
        this.name = name;
        this.javaType = javaType;
    }

    public String getName() {
        return name;
    }

    public String getJavaType() {
        return javaType;
    }
}
