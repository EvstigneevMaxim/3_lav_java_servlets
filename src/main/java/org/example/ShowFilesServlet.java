package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/list-files"})
public class ShowFilesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String directoryPath = req.getParameter("path");
        if (directoryPath == null) {
            directoryPath = "D:/";
        }
        File folder = new File(directoryPath);
        File[] files = folder.listFiles(File::isFile);
        File[] folders = folder.listFiles(File::isDirectory);
        req.setAttribute("files", files);
        req.setAttribute("folders", folders);
        req.setAttribute("currentPath", directoryPath);
        req.setAttribute("previousPath",  (new File(directoryPath)).getParent());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
