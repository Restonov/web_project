package by.restonov.jspservlet.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public SessionRequestContent() {
    }

    public void extractValues(HttpServletRequest request) {

    }

    public void insertAttributes (HttpServletRequest request) {

    }
}
