package ru.team_grade.grade_mobile;

class User {
    static String login;
    static String pass;
    static String token;
    static String response;

    User(String login, String pass, AsyncResponse delegate) {
        User.login = login;
        User.pass = pass;
        token = null;
        LoginTask task = new LoginTask();
        task.delegate = delegate;
        task.execute();
    }
}
