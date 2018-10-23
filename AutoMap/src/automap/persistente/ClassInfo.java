/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.persistente;

import automap.IOHandlerDir;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

/**
 *
 * @author hmia
 */
public class ClassInfo {

    private String pack;
    private String clase;
    private Property id;
    private HashSet<Property> propiedades;
    private HashSet<Relation> relaciones;
    
    public ClassInfo(File file) throws FileNotFoundException {
        AbstractorClass clase = new AbstractorClass(file);
        this.pack = clase.getPackage();
        this.clase = clase.getClassName();
        this.propiedades = clase.getProperties();
        this.relaciones = clase.getRelations();
    }

    public String getNombre() {
        return clase;
    }

    private String tipoDeMapeoNormal() {
        return "";
    }

    private String tipoDeMapeoBidireccional() {
        return "";
    }

    private class AbstractorClass {

        private String[] lineas;

        public AbstractorClass(File f) throws FileNotFoundException {
            String[] l = IOHandlerDir.readFile(f);
            lineas = l;
        }

        public String getPackage() {
            return "";
        }

        public String getClassName() {
            return "";
        }

        public HashSet<Property> getProperties() {
            return null;
        }

        public HashSet<Relation> getRelations() {
            return null;
        }

        public boolean isPersistente() {
            return true;
        }
    }
}
