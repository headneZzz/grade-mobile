package ru.team_grade.grade_mobile;

public class User {
    static String login;
    static String pass;
    static String token;
    static String response;
    static String name;
    static String department;
    static String direction;
    static String degree;
    static String type;
    static String navLogin;
    static String email;


    User(String login, String pass, AsyncResponse delegate) {
        User.login = login;
        User.pass = pass;
        token = null;
        LoginTask task = new LoginTask();
        task.delegate = delegate;
        task.execute();
    }
}
