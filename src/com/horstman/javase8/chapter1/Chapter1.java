package com.horstman.javase8.chapter1;

import java.io.File;
import java.util.Arrays;

import static com.horstman.javase8.chapter1.RunnableEx.uncheck;

public class Chapter1 {

    public File[] getSubdirectoriesLambda(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.listFiles((File currentFile, String name) -> {
            return new File(currentFile, name).isDirectory();
        });
    }


    public File[] getSubdirectoriesMethodReference(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.listFiles(File::isDirectory);
    }

    public String[] getFilesFromDirectory(String directoryPath, String extension) {
        File directory = new File(directoryPath);
        return directory.list((File currentFile, String name) -> {
            return new File(currentFile, name).isFile() && name.endsWith(extension);
        });
    }

    public void sortArray(File[] fileArray) {
        Arrays.sort(fileArray, (file1, file2) -> {
            if (file1.isDirectory()) {
                return file2.isDirectory() ? file1.compareTo(file2) : -1;
            } else if (file2.isDirectory()) {
                return 1;
            }
            return file1.compareTo(file2);
        });
    }

    public static Runnable andThen(Runnable runnable1, Runnable runnable2) {
        return () -> {
            runnable1.run();
            runnable2.run();
        };
    }

    public static void main(String[] args) {
        new Thread(andThen(() -> System.out.println("first"), () -> System.out.println("second"))).start();
        new Thread(uncheck(
                () -> {
                    System.out.println("Zzz");
                    Thread.sleep(1000);
                })).start();
    }
}
