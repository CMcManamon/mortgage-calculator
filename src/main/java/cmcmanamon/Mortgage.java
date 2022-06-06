package cmcmanamon;


public class Mortgage {
	private double principal;
	private double interest;
	private double monthlyPayment;
	private int numMonths;
	private double totalInterest;

	public Mortgage(double principal, double interest, double monthlyPayment) {
		super();
		this.principal = principal;
		this.interest = interest;
		this.monthlyPayment = monthlyPayment;
		calculate();
		
	}
	
	private void calculate() {
		numMonths = 0;
		totalInterest = 0.0;
		double monthlyRate = interest / 1200;
		double pr = principal;
		while (pr > 0) {
			numMonths++;
			double interestThisMonth = pr * monthlyRate;
			totalInterest += interestThisMonth;
			pr -= (monthlyPayment - interestThisMonth) < pr ? (monthlyPayment - interestThisMonth) : pr;			
		}
	}
	
	public double getPrincipal() {
		return principal;
	}
	
	public void setPrincipal(double principal) {
		this.principal = principal;
		calculate();
	}
	
	public double getInterest() {
		return interest;
	}
	
	public void setInterest(double interest) {
		this.interest = interest;
		calculate();
	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
		calculate();
	}
	
	public int getNumMonths() {
		return numMonths;
	}
		
	public double getTotalInterest() {
		return totalInterest;
	}
	
	public String getDuration() {
		String dur = "";
		if (numMonths / 12 > 0)
			dur += (numMonths / 12) + " years, ";
		dur += (numMonths % 12) + " months";
		return dur;
	}
}