/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap;

import automap.exceptions.NoPersistenteClassException;
import automap.io.ConsoleMenu;
import automap.io.Util;
import automap.parsing.PersistenteClass;
import automap.xml.Tag;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hmia
 */
public class AutoMap {

    private static String projectURL;
    private static File[] filesToMap;
    private static final List<PersistenteClass> persistenteClassList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        init();
        boolean exit = false;
        do {
            switch (ConsoleMenu.exec("Define url project (current = " + projectURL + ")?Analize project?Generate Hibernate mappings for project")) {
                case ConsoleMenu.EXIT: {
                    ConsoleMenu.printClose();
                    exit = true;
                    break;
                }
                case 1: {
                    boolean isValid = false;
                    do {
                        System.out.println("Write or paste a valid URL:\n");
                        projectURL = Util.readString();
                        File f = new File(projectURL);
                        if (f.exists() && !f.isDirectory()) {
                            isValid = true;
                        }
                    } while (!isValid);
                    ConsoleMenu.printJump();
                    break;
                }
                case 2: //Analize project
                {
                    System.out.println("\n....................................................");
                    System.out.println("                  Analize project                 ....");
                    System.out.println("....................................................");
                    System.out.println("                    Finish                           ");
                    analizeProject();
                    System.out.println("Finish");
                    break;
                }
                case 3: //Generate Hibernate mappings for project
                {

                    System.out.println("\n....................................................");
                    System.out.println("Generate Hibernate mappings for project project ....");
                    System.out.println("....................................................");
                    generateMappings();
                    System.out.println("....................................................");
                    System.out.println("                    Finish                           ");
                    System.out.println("....................................................");
                    break;
                }
            }
        } while (!exit);
    }

    private static void init() {
        ConsoleMenu.setAppName("auto map");
        projectURL = ".";
    }

    private static void analizeProject() {
        File f = new File(projectURL);
        filesToMap = IOHandler.getContent(f, "java");
        IOHandler.displayContent(f, "java");
        
        for (File file : filesToMap) {
            try {
                PersistenteClass persistenteClass = new PersistenteClass(file);
                persistenteClassList.add(persistenteClass);
                
            } catch (FileNotFoundException ex) {

            } catch (NoPersistenteClassException ex) {
                if (ex.hasMessages()) {
                    System.out.println("El fichero =>" + f.getName() + " no se pudo parsear debido a que no es una clase persitente por:\n");
                    while (ex.getMessages().iterator().hasNext()) {
                        System.out.println(ex.getMessages().iterator().next());
                    }
                }
            }
        }
    }

    /**
     * Genera el xml mappping
     */
    private static void generateMappings() {
        persistenteClassList.stream().forEach((PersistenteClass c) -> {
            String content = converPersistClassToHibernateXmlMapping(c);
            try {
                if (c.hasWarnigs()) {
                }
                String fileName = projectURL + c.getClassName().getName() + ".xml";
                IOHandler.writeFile(fileName, content);
            } catch (IOException ex) {
                System.out.println("Error al escribir el fichero =>"
                        + c.getClassName().getName());
            }
        });
    }

    /**
     * Conver a persistente class to xml mapping
     *
     * @param c
     * @return
     */
    private static String converPersistClassToHibernateXmlMapping(PersistenteClass c) {
        Tag root = new Tag("hibernate-mapping#package/" + c.getPackName().getName().trim()).setLabel("root");
        Tag tagClass = new Tag("class#name/" + c.getClassName().getName().trim() + "/table/"
                + "TBL_" + c.getClassName().getName().toUpperCase().trim()).setLabel("class");
        Tag id = new Tag("id#name/" + c.getId() + "/column/EVENT_ID").setLabel("id");
        Tag generator = new Tag("generator#class/native").setLabel("generator");

        id.addTag(generator);
        tagClass.addTag(id);

        //recorremos cada una de las propiedades de nuestra clase persistente y la añadimos como tag
        c.getProperties().stream().forEach((property) -> {
            tagClass.addTag(new Tag("property#name/" + property.getName() + "/type/" + getHibernateType(property.getJavaType())).addTag(new Tag("column#column" + property.getName().toUpperCase())).setLabel(""));
        });

        //tendriamos que recorrer tambien las colecciones, las diferentes relaciones y añadir su tag a tagClass        
        root.addTag(tagClass);
        return root.toXmlHibernateMapping();
    }

    /**
     * Convierte el tipo de java al de hibernate
     * @param javaType
     * @return 
     */
    private static String getHibernateType(String javaType) {
        switch (javaType.toLowerCase()) {
            case "int":
                return "integer";
            case "long":
                return "long";
            case "short":
                return "short";
            case "float":
                return "float";

            case "double":
                return "double";

            case "char":
                return "character";

            case "byte":
                return "byte";

            case "boolean":
                return "boolean";

            case "date":
                return "timestamp";

            case "string":
                return "text";

        }
        return javaType;

    }

}
