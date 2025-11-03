package controller;

import java.io.File;
import java.io.FileInputStream; // New import for filesystem access
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User; // Assuming models.User exists in your project

@WebServlet("/showPic.do")
public class ShowMedicinePicServlet extends HttpServlet {
    
    // NOTE: Replace this placeholder with the actual, absolute path 
    // where your UploadServlet saves the images (e.g., "C:/app_uploads/images").
    // IMPORTANT: This path MUST match the path used by your upload code.
    private static final String UPLOAD_BASE_DIR = "/WEB-INF/uploads/"; // Keeping the old path structure, but it's often better to define a system path.
    private static final int BUFFER_SIZE = 4096;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        String picPath = request.getParameter("pic_path");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        // --- 1. Validation and Path Construction ---
        if (user == null || user.getUserId() == null || picPath == null || picPath.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User or image path not provided.");
            return;
        }
        
        // The file is located at the absolute path:
        // C:/path/to/webapps/AppName/WEB-INF/uploads/userId/imageName.png
        String basePath = getServletContext().getRealPath(UPLOAD_BASE_DIR);
        
        // Construct the full absolute file path on the server's disk
        String fullAbsolutePath = basePath + File.separator + user.getUserId() + File.separator + picPath;

        File file = new File(fullAbsolutePath);
        
        // Log the path being checked (for debugging)
        System.out.println("Attempting to load image from path: " + fullAbsolutePath);
        
        // --- 2. CRITICAL FIX: Use try-with-resources for guaranteed closure ---
        try (
            // Use FileInputStream to read the file directly from the disk path
            InputStream is = new FileInputStream(file);
            OutputStream os = response.getOutputStream()
        ) {
            
            // --- 3. Handle Missing Resource ---
            if (!file.exists() || file.isDirectory()) {
                // Send a 404 error if the resource couldn't be found
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image resource not found at: " + fullAbsolutePath);
                return;
            }
            
            // --- 4. Set Content Type for the Browser ---
            // Use the ServletContext to determine the correct MIME type based on the file name
            String mimeType = getServletContext().getMimeType(file.getName());
            response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
            
            // --- 5. Stream the file content ---
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            
            // Ensure all buffered data is sent to the client
            os.flush();
            
        } catch (IOException e) {
            // Log the error if streaming fails or file cannot be opened (and wasn't caught by file.exists())
            // This also catches the FileNotFoundException from FileInputStream if file.exists() was bypassed
            System.err.println("I/O Error during image streaming from path: " + fullAbsolutePath);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading image file.");
            e.printStackTrace();
        }
    }
}