package com.scsu.ia;

import java.io.IOException;
import java.io.PrintWriter;
 
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @purpose UserInformation Servlet is in charge of communicate with Client side
 * 
 */
public class UserInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String protection = request.getParameter("protection");
		 
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("text/html");
                response.setHeader("Cache-control", "no-cache, no-store");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", "-1");
                
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "POST");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type");
                response.setHeader("Access-Control-Max-Age", "86400");
                
                JsonObject myObj = null;
                if("without".equals(protection)){
                    myObj = new DBManager().getInfo(userId,userPass);
                }else{
                    myObj = new DBManager().getInfoProtection(userId,userPass);
                }
                System.out.println(myObj.toString());
                out.println(myObj.toString());
                System.out.println("end of the Servlet!");
            }
	}

	
}
