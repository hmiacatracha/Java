/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automap;

import automap.util.DirectoryNotFoundException;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hmia
 */
public class DirectoryAutoMap {

    private File directory;
    private ArrayList<File> fileList;

    public DirectoryAutoMap(String path) {
        File dir = new File(path);
        fileList = new ArrayList<>();
        if (!dir.isDirectory()) {
            throw new DirectoryNotFoundException(path);
        }
        this.directory = dir;
    }

    public File getDirectory() {
        return directory;
    }

    public String getCanonicalPath() {
        try {
            return this.directory.getCanonicalPath();
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Display the directory content
     */
    public void displayDirectoryContent() {
        printContent(DirectoryAutoMap.this.getDirectoryContent());
    }

    /**
     * Display the directory content with filter FileFilter
     *
     * @param filter
     */
    public void displayDirectoryContent(FileFilter filter) {
        printContent(DirectoryAutoMap.this.getDirectoryContent(filter));
    }

    /**
     * Display the directory content with filter FilenameFilter
     *
     * @param filter
     */
    public void displayDirectoryContent(FilenameFilter filter) {
        printContent(DirectoryAutoMap.this.getDirectoryContent(filter));
    }

    /**
     * Display the directory recursive content
     */
    public void displayRecursiveContent() {
        printContent(DirectoryAutoMap.this.getRecursiveDirectoryContent());
    }

    /**
     * Display the directory recursive content with Filter FileFilter
     *
     * @param filter
     */
    public void displayRecursiveContent(FileFilter filter) {
        printContent(DirectoryAutoMap.this.getRecursiveDirectoryContent(filter));
    }

    /**
     * Display the directory recursive content with Filter FilenameFilter
     *
     * @param filter
     */
    public void displayRecursiveContent(FilenameFilter filter) {
        printContent(DirectoryAutoMap.this.getRecursiveDirectoryContent(filter));
    }

    /**
     * get directory content
     *
     * @return
     */
    public File[] getDirectoryContent() {
        fileList = new ArrayList<>();
        return this.directory.listFiles();
    }

    /**
     * Get directory content with filter FileFilter
     *
     * @param filter
     * @return
     */
    public File[] getDirectoryContent(FileFilter filter) {
        fileList = new ArrayList<>();
        return this.directory.listFiles(filter);
    }

    /**
     * Get the directory content with the filter FilenameFilter
     *
     * @param filter
     * @return
     */
    public File[] getDirectoryContent(FilenameFilter filter) {
        fileList = new ArrayList<>();
        return this.directory.listFiles(filter);
    }

    /**
     * Get the recursive directory content
     *
     * @return
     */
    public File[] getRecursiveDirectoryContent() {
        fileList = new ArrayList<>();
        return recursiveDirectoryContents(this.directory);
    }

    /**
     * Get the recursive directory content with filter FilenameFilter
     *
     * @param filter
     * @return
     */
    public File[] getRecursiveDirectoryContent(FilenameFilter filter) {
        fileList = new ArrayList<>();
        return recursiveDirectoryContents(this.directory, filter);
    }

    /**
     * Get the recursive directory content with the filter FileFilter
     *
     * @param filter
     * @return
     */
    public File[] getRecursiveDirectoryContent(FileFilter filter) {
        fileList = new ArrayList<>();
        return recursiveDirectoryContents(this.directory, filter);
    }

    private File[] recursiveDirectoryContents(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                recursiveDirectoryContents(file);
            } else {
                fileList.add(file);
            }
        }
        return fileList.toArray(new File[fileList.size()]);
    }

    private File[] recursiveDirectoryContents(File dir, FileFilter filter) {
        ArrayList<File> fileList = new ArrayList<>();
        for (File file : dir.listFiles(filter)) {
            if (file.isDirectory()) {
                recursiveDirectoryContents(file, filter);
            } else {
                fileList.add(file);
            }
        }
        return fileList.toArray(new File[fileList.size()]);
    }

    private File[] recursiveDirectoryContents(File dir, FilenameFilter filter) {
        ArrayList<File> fileList = new ArrayList<>();
        for (File file : dir.listFiles(filter)) {
            if (file.isDirectory()) {
                recursiveDirectoryContents(file, filter);
            } else {
                //System.out.println("file:" + file.getCanonicalPath());
                fileList.add(file);
            }
        }
        return fileList.toArray(new File[fileList.size()]);
    }

    private void printContent(File[] files) {
        for (File f : files) {
            try {
                System.out.println(f.getCanonicalPath());
            } catch (IOException ex) {

            }
        }
    }
}
