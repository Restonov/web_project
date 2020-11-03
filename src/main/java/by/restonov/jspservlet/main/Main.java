package by.restonov.jspservlet.main;

import by.restonov.jspservlet.util.MailSender;
import by.restonov.jspservlet.util.UserPasswordAuthentication;

import java.util.Enumeration;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {


        char[] password = new char[]{'1', '1', '1', '1', '1', '1', '1'};

        UserPasswordAuthentication authentication = new UserPasswordAuthentication();
        String hashedPass = authentication.hash(password);

        MailSender sender = new MailSender();

        System.out.println(hashedPass);


        Enumeration enumeration;

        Vector<String> list = new Vector<>();
        list.add("Hello");
        list.add("Hello");
        enumeration = list.elements();

        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher("Boris");
      //  System.out.println(matcher.find());

    }
}
