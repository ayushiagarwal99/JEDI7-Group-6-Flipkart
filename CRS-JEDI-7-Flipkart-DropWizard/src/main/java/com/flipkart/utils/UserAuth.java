package com.flipkart.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserAuth {
    public static Map<String, String> loggedInStudents = new HashMap<>();
    public static Map<String, String> loggedInProfessor = new HashMap<>();
    public static Map<String, String> loggedInAdmin = new HashMap<>();

    public static String generateRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


    public static String loginProfessor(String profId) {
        String key = generateRandomString();
        loggedInProfessor.put(key, profId);
        return key;
    }

    public static void logoutProfessor(String key) {
        loggedInProfessor.remove(key);
    }

    public static String isProfessorLogin(String key) {
        return loggedInProfessor.get(key);

    }
    public static String loginAdmin(String adminId){
        String key = generateRandomString();
        loggedInAdmin.put(key,adminId);
        return key;
    }
    public static void logoutAdmin(String key){
        loggedInAdmin.remove(key);
    }

    public static String isAdminLogin(String key){
        return loggedInAdmin.get(key);

    }






    public static String loginStudent(String studentId){
        String key = generateRandomString();
        loggedInStudents.put(key,studentId);
        return key;
    }
    public static void logoutStudent(String key){
        loggedInStudents.remove(key);
    }

    public static String isStudentLogin(String key){
        return loggedInStudents.get(key);

    }



    public static Boolean isUserLogin(String key){
        return loggedInStudents.containsKey(key) || loggedInAdmin.containsKey((key)) || loggedInProfessor.containsKey(key);
    }
}