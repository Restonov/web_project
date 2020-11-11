package by.restonov.tyrent.controller;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.factory.ActionFactory;
import by.restonov.tyrent.model.exception.ConnectionPoolException;
import by.restonov.tyrent.manager.MessageManager;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.model.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="Controller", urlPatterns = {"/controller", "/login", "/register", "/main"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String page;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = PageName.INDEX_PAGE;
            //TODO page 404
            session.setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.INSTANCE.shutdownPoll();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
