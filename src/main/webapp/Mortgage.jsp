<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList, java.text.NumberFormat, java.text.DecimalFormat" %>
<%@page import="cmcmanamon.Mortgage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mortgage Calculator</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="row">
  <div class="column left">
    <h2><center>Mortgage Calculator</center></h2>
    <!--  Form to get inputs -->
    <form action="Mortgage" method="post" onsubmit="return validateForm(event)">
      <div class="formField">
    	<label for="principal">Principal: $</label>
    	<input type="text" size="14" id="principal" name="principal" value="${sPrincipal}">
      </div>
 	  <div class="formField">
    	<label for="interestRate">Interest Rate: %</label>
    	<input type="text" size="6" id="interestRate" name="interestRate" value="${sInterest}">
      </div>
      <div class="formField">
    	<label for="monthlyPayment">Monthly Payment: $</label>
    	<input type="text" size="14" id="monthlyPayment" name="monthlyPayment" value="${sMonthly}">
      </div>
    	<input type="button" id="reset" name="reset" value="Reset">
    	<input type="submit" id="submit" name="submit" value="Submit">
    	<br><br>
    	
    	<!--  Results of calculations and error messages -->
    	<div id="results">    	    
    	<%
    	NumberFormat dollarFormat = NumberFormat.getCurrencyInstance();
    	int getMonths, years, months;
    	double getInterest;
    	if (session.getAttribute("numMonths") != null) {
	   		getMonths = (int) session.getAttribute("numMonths");
        	years = getMonths / 12;
       		months = getMonths % 12;
	    	%>Mortgage paid in<% if (years > 0) { %> <%=years%> years<% }%>
 			<%=months%> months<br>
        	Total Interest: <%=dollarFormat.format(session.getAttribute("totalInterest"))%></p>    			
  		<% } %>
 		</div>
    	<!--  Script to handle validation -->
   	    <script type="text/javascript" src="validateForm.js"></script>
    
  </div>
  <div class="column right">
    <h2><center>Log</center></h2>
	<!--  Log entries -->
    <%
	DecimalFormat decFormat = new DecimalFormat("0.00");
    ArrayList<Mortgage> log = (ArrayList<Mortgage>) session.getAttribute("log");
    	if (log != null) {
   		%><div align="center"><button type="submit" name="submit" value="clearAll">Clear All</button></div>
   			<table id="logTable"><tr><th>Principal</th><th>Interest Rate (%)</th><th>Monthly Payment</th>
   			<th>Duration</th><th>Total Interest</th><th></th></tr>		
  		<%
          for (Mortgage entry : log) {
    	%>
    		<tr><td><button type="button" class="cPrincipal" name="logPrincipal" value="<%=decFormat.format(entry.getPrincipal()) %>"><%=dollarFormat.format(entry.getPrincipal()) %></button></td>
    		<td><button type="button" class="cInterest" name="logInterest" value="<%=entry.getInterest() %>"><%=entry.getInterest() %></button></td>
    		<td><button type="button" class="cMonthly" name="logMonthly" value="<%=decFormat.format(entry.getMonthlyPayment()) %>"><%=dollarFormat.format(entry.getMonthlyPayment()) %></button></td>
    		<td><input type="text" class="cNumMonths" name="logNumMonths" value="<%=entry.getDuration() %>" readonly></td>
    		<td><input type="text" size="14" class="cTotalInterest" name="logTotalInterest" value="<%=dollarFormat.format(entry.getTotalInterest()) %>" readonly></td>
    		<td><button type="submit" name="deleteEntry" value="<%=log.indexOf(entry) %>">Delete</button></td></tr>
    	<% } %>
			</table>
			</form>	
		    <script type="text/javascript" src="logValues.js"></script>
    	<% }%>
  </div>
</div>

</body>
</html>