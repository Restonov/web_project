package by.restonov.tyrent.controller;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.controller.factory.ActionFactory;
import by.restonov.tyrent.controller.command.CommandEnum;
import by.restonov.tyrent.model.exception.ConnectionPoolException;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


/**
 * Controller - the main application servlet
 * that processing all requests to the server
 * and forms responses
 */
@WebServlet(name="Controller", urlPatterns = {"/controller", "/login", "/register", "/main"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    /**
     * default GET method
     *
     * @param request - HttpServletRequest
     * @param response - HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            processRequest(request, response);
        } catch (ServletException | IOException e) {
            logger.fatal("Servlet fatal error", e);
        }
    }

    /**
     * POST method
     *
     * @param request - HttpServletRequest
     * @param response - HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            processRequest(request, response);
        } catch (ServletException | IOException e) {
            logger.fatal("Servlet fatal error", e);
            }
    }

    /**
     * Process receiving request and
     * delegates definition of the command to the {@link ActionFactory}
     * if {@link CommandEnum} contains command -> forward User to result page
     * otherwise redirect User to the 404 page
     *
     * @param request - HttpServletRequest
     * @param response - HttpServletResponse
     * @throws ServletException - general exception of a servlet can throw when it encounters difficulty.
     * @throws IOException - Signals that an I/O exception of some sort has occurred
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        String page;
        Optional<ActionCommand> optionalCommand = ActionFactory.defineCommand(request);
        if (optionalCommand.isPresent()) {
            ActionCommand command = optionalCommand.get();
            page = command.execute(request);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + PageName.ERROR_404_PATH);
        }
    }

    /**
     * If application stops, try to
     * shutdown {@link ConnectionPool}
     */
    @Override
    public void destroy() {
        try {
            ConnectionPool.INSTANCE.shutdown();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
