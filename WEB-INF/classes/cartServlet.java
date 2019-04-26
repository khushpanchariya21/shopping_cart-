import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class cartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s,item[]={"iphone","phone","mac","dell"};
		double price []={600,600,1200,1200};
        double cost,totalCost;
        int initial=0;
        totalCost=0;

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
			
		HttpSession session=request.getSession(false);

        for (int i = 0; i < item.length; i++)
        	if ( session.getAttribute(item[i]) == null )
            	session.setAttribute(item[i], initial);

		if(session!=null){
			
		if ( (s = request.getParameter("buy")) != null ) {
            int n = ((Integer)session.getAttribute(s)).intValue();
            session.setAttribute(s,(n + 1));
        }
        out.println("<h2>YOUR CART</h2><ul>");
        out.println("<table  class='table'><thead><th>ItemName</th><th>Quantity</th><th>Price</th><th>Total</th></thead><tbody>");
        for (int i = 0; i < item.length; i++) {
            int n = ((Integer)session.getAttribute(item[i])).intValue();
            
	            if ( n > 0 ){
	                out.println("<tr><td>" + item[i] + "</td><td>" + n +"</td><td>"+ price[i] +"</td><td>");
	                cost=n*price[i];
	                out.println(cost+"</td></tr>");
	                totalCost+=cost;
	            }
            }
        out.println("<tr><td></td><td></td><td></td><td><b>You have to Pay DOLLAR: $</b>"+totalCost+"</td>"); 
        out.println("<tr><td><button class='btn btn-info' >Checkout</button><td>");   
        out.println("</tbody></table></body></html>");

		}
		else{
			out.print("Please login first");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
	}
}
