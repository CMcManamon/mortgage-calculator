/**
 * Validates form inputs
 */

// Listener for Reset Button
document.getElementById("reset").addEventListener("click", resetForm);

var resultsText = document.getElementById("results"); // Where results or error msg goes
const errorStart = "<font color=\"red\">Error: ";
const errorEnd = "</font><br>";

// Called when submit buttons are clicked
function validateForm(event) {
	
	if (event.submitter.value != "Submit")
		return true;
		
	// Input fields
	var mPri = document.getElementById("principal").value;
	var mInt = document.getElementById("interestRate").value;
	var mMon = document.getElementById("monthlyPayment").value;

	// Initial error text is blank
	resultsText.innerHTML = "";
	var hasErrors = false;

	// Validate money
	var currencyPattern = /^(\d+|\d+\.(\d|\d\d))$/; // Currency
	if (!currencyPattern.test(mPri) || mPri <= 0) {
		resultsText.innerHTML += errorStart + "Invalid input for Principal" + errorEnd;
		hasErrors = true;
	}
	
	// Validate interest rate
	if (mInt <= 0 || mInt >= 100) {
		resultsText.innerHTML += errorStart + "Invalid input for Interest Rate" + errorEnd;
		hasErrors = true;		
	}

	if (!currencyPattern.test(mMon)) {
		resultsText.innerHTML += errorStart + "Invalid input for Monthly Payment" + errorEnd;
		hasErrors = true;
	}
		
	if (hasErrors)		
		return false;

	// Inputs are valid numbers, now check for logic
	// Monthly payment must be larger than the monthly interest on the initial principal.
	if (mMon <= (mInt / 1200) * mPri) {
		resultsText.innerHTML += errorStart + "Monthly payment too small to pay back the loan at this rate" + errorEnd;
		return false;
	}
	
	return true;
}

function clearResults() {
	resultsText.innerHTML = "";
}

function resetForm() {
	document.getElementById("principal").value = "";
	document.getElementById("interestRate").value = "";
	document.getElementById("monthlyPayment").value = "";
	resultsText.innerHTML = "";
}