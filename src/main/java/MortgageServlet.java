

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cmcmanamon.Mortgage;

/**
 * Servlet implementation class MortgageServlet
 */
@WebServlet("/Mortgage")
public class MortgageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MortgageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Refreshed servlet without values, load the default page
		if (request.getParameter("submit") == null && request.getParameter("deleteEntry") == null) {
			request.getRequestDispatcher("Mortgage.jsp").forward(request, response);
			return;
		}

		// Get session
		HttpSession session = request.getSession();
		
		ArrayList<Mortgage> log; // log of previous inputs and results
		if (session.getAttribute("log") == null) // no log, so start one
			log = new ArrayList<>();
		else
			log = (ArrayList<Mortgage>) session.getAttribute("log");
		

		// input values
		String inputPrincipal = request.getParameter("principal");
		String inputInterest = request.getParameter("interestRate");
		String inputMonthly = request.getParameter("monthlyPayment");

		// save values currently in input fields
		session.setAttribute("sPrincipal", inputPrincipal);
		session.setAttribute("sInterest", inputInterest);
		session.setAttribute("sMonthly", inputMonthly);
				
		// User clicked Clear All button
		if (request.getParameter("submit") != null && request.getParameter("submit").equals("clearAll")) {
			// reset the log
			log.clear();
			session.removeAttribute("log");
		}
		// User clicked Delete button on an entry
		else if (request.getParameter("deleteEntry") != null) {
			// delete the specified entry
			int entryNum = Integer.parseInt(request.getParameter("deleteEntry"));
			if (entryNum < log.size())
				log.remove(entryNum);
			if (log.size() == 0)
				session.removeAttribute("log");
		}
		// User clicked submit with data
		else {		
			double principal = Double.parseDouble(inputPrincipal);
			double interestRate = Double.parseDouble(inputInterest);
			double monthlyPayment = Double.parseDouble(inputMonthly);

			Mortgage mortgage = new Mortgage(principal, interestRate, monthlyPayment);
			
			// Result 1 : duration of mortgage in years and months
			int numMonths = mortgage.getNumMonths();
			session.setAttribute("numMonths", numMonths);
			
			// Result 2 : total amount towards paying the interest
			double totalInterest = mortgage.getTotalInterest();
    		java.text.NumberFormat formatter = java.text.NumberFormat.getCurrencyInstance();
    		String moneyString = formatter.format(totalInterest);
			session.setAttribute("totalInterest", totalInterest);
						
			// Add result to logs
			log.add(0, mortgage);
			session.setAttribute("log", log);

		}
		request.getRequestDispatcher("Mortgage.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
