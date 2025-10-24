package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import models.MedicineDenominationImage;
import models.User;

@WebServlet("/uploadPic.do")
public class UploadPicServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        // Set content type for response
        response.setContentType("text/plain");
        String message = "Pic Upload Nahi Hua!!";

        // Variables to be extracted from multipart data
        FileItem fileItem = null;
        String denominationIdStr = null;
        Integer denominationId = null; // Stays null until successfully parsed
        
        if(ServletFileUpload.isMultipartContent(request)) {
            try {
                DiskFileItemFactory dfif = new DiskFileItemFactory();
                ServletFileUpload sfu = new ServletFileUpload(dfif);
                List<FileItem> fileItems = sfu.parseRequest(request);

                // 1. Extract file and denominationIdStr
                for (FileItem item : fileItems) {
                    if (item.isFormField()) {
                        if ("denominationId".equals(item.getFieldName())) {
                            denominationIdStr = item.getString();
                        }
                    } else {
                        fileItem = item;
                    }
                }
                
                // 2. CRITICAL VALIDATION AND PARSING
                if (user == null) {
                    message = "Pic Upload Nahi Hua!! (User not logged in or session expired)";
                } else if (fileItem == null || denominationIdStr == null || denominationIdStr.trim().isEmpty()) {
                    message = "Pic Upload Nahi Hua!! (Missing Denomination ID or File)";
                } else {
                    // This block executes ONLY if we have the file and the string ID
                    try {
                        // Safely parse the integer (will throw NumberFormatException if invalid)
                        denominationId = Integer.parseInt(denominationIdStr.trim());
                        
                        // --- File Processing Logic ---

                        // System.out.println(fileItem.isFormField() + "-----------------------");
                        
                        String fileName = fileItem.getName();
                        // Ensure the path is created using the correct user ID
                        String uploadFolder = getServletContext().getRealPath("/WEB-INF/Uploads/"+ user.getUserId());
                        
                        // Ensure directory exists (MANDATORY check)
                        File folder = new File(uploadFolder);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }

                        // System.out.println(fileName + " ^^^^^^^^^^^^^^^^^");
                        // System.out.println(uploadFolder + " ^^^^^^^^^^^^^^^^^");

                        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                        Date dt = new Date();
                        String newFileName = dt.getTime() + fileExtension;   
                        File file = new File(uploadFolder , newFileName);

                        String picPath = user.getUserId()+"/"+newFileName;
                        
                        // --- File Write and DB Save ---
                        fileItem.write(file);
                        MedicineDenominationImage.savePicPath(denominationId , picPath);
                        
                        // SUCCESS: Return the picPath with the separator for client-side parsing
                        message = "PIC UPLOAD HO GYA --------:" + picPath; 
                        
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        message = "Pic Upload Nahi Hua!! (Invalid Denomination ID format)";
                    } catch (Exception e) {
                        // Catches File Write errors and DB errors (SQLException)
                        e.printStackTrace();
                        message = "Pic Upload Nahi Hua!! (File Write/DB Error)";
                    }
                }
                
            } catch(FileUploadException e) {
                e.printStackTrace();
                message = "Pic Upload Nahi Hua!! (Parsing Error)";
            }
        }

        response.getWriter().write(message);
    }
}