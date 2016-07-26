package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Servlet implementation class PepaServlet
 */
@WebServlet("/PepaServlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.write("IN GET METHOD - Served at: " + request.getContextPath());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
    	PrintWriter out = response.getWriter();
    	
    	out.write("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
    	out.write("<html>");
    	out.write("<head>");
    	out.write("<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>");
    	out.write("<title>Openmatics REST serice DEMO</title>");
    	out.write("<link rel='stylesheet' type='text/css' href='Novy3.css'>");
    	out.write("</head>");
    	out.write("<body>");
    	out.write("<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'> ");
    	out.write("<div class='login-block'>");
    	
    	String city = request.getParameter("city");
		out.write("Vyhledej = " + city + " </p>");
		
		System.out.println(out);	
	
		
		// REST service call
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		 
		HttpHost target = new HttpHost("query.yahooapis.com", 80, "http");
	    HttpGet getRequest = new HttpGet("/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"
	    		+ city 
	    		+ "%2C%20Czech%22)&format=xml&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
		
		// out.write("executing request to " + target + " </p>");
		 
	    HttpResponse httpResponse = httpclient.execute(target, getRequest);
	    HttpEntity entity = httpResponse.getEntity();
		
	    out.write("----------------------------------------" + " </p>");
	   
	    //out.println(httpResponse.getStatusLine());
	    
	    String output = EntityUtils.toString(entity);
	    
	    output = output.replace("&lt;BR /&gt;", "<br>"); 
		output = output.replace("&lt;b&gt;", ""); 
		output = output.replace("&lt;/b&gt;", "");
	    
		// remove dummy text from beggining and end
		String[] tokens = output.split("Current Conditions");
		String[] tokens2 = tokens[1].split("&lt;a");
		
	    out.println("Current Conditions" + tokens2[0]);
	    
	    
	    if (entity != null) {
	    	System.out.println(output);	
	    }else
	    	out.println("Not found");
		
	}

}
