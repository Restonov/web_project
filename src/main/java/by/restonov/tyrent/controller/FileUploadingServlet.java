package by.restonov.tyrent.controller;

import by.restonov.tyrent.manager.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
//TODO use it or remove it
@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)

public class FileUploadingServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "E:\\tmp\\";
    public static final String SUCCESFULL_UPLOAD = "success";
    public static final String NOT_SUCCESFULL_UPLOAD = "not success";
    public static final String RESULT_JSP = ConfigurationManager.getProperty("path.page.upload");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        File fileSaveDir = new File(UPLOAD_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        request.getParts().stream().forEach(part -> {
            try {
                String path = part.getSubmittedFileName();
                String randFilename = UUID.randomUUID()+path.substring(path.lastIndexOf("."));
                part.write(UPLOAD_DIR  + randFilename);
                request.setAttribute("upload_result", SUCCESFULL_UPLOAD);
            } catch (IOException e) {
                request.setAttribute("upload_result", NOT_SUCCESFULL_UPLOAD);
            }
        });
        request.getRequestDispatcher(RESULT_JSP).forward(request, response);
    }
}
