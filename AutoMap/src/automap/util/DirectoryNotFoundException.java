/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap.util;

/**
 *
 * @author hmia
 */
public class DirectoryNotFoundException extends RuntimeException{
    
    public DirectoryNotFoundException(){
        super("The path is not a directory");
    }
    
    public DirectoryNotFoundException(String path){
        super("The path " + path + " is not a directory");
    }
}
