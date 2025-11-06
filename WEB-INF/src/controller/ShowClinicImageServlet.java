package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

@WebServlet("/showClinicImage.do")
public class ShowClinicImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String picPath = request.getParameter("pic_path");

        if (picPath.endsWith(".png")) {
            response.setContentType("image/png");
        } else if (picPath.endsWith(".jpg") || picPath.endsWith(".jpeg")) {
            response.setContentType("image/jpeg");
        } else {
            response.setContentType("application/octet-stream");
        }

        String fullPath = "/WEB-INF/uploads/"+picPath;

        ServletOutputStream sos = response.getOutputStream();
        InputStream is = getServletContext().getResourceAsStream(fullPath);
        
         if (is == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found at: " + fullPath);
            return;
        }

        byte[] arr = new byte[512];
        while (is.read(arr) != -1) {
            sos.write(arr);
        }
        sos.close();
    }
}