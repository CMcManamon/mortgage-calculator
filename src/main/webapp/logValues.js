/**
 * Attach listeners to log entry buttons and manage their actions
 */

const pButtons = document.querySelectorAll('.cPrincipal');
for (const btn of pButtons) {
	btn.addEventListener('click', setPrincipal);
}

const iButtons = document.querySelectorAll('.cInterest');
for (const btn of iButtons) {
	btn.addEventListener('click', setInterest);
}

const mButtons = document.querySelectorAll('.cMonthly');
for (const btn of mButtons) {
	btn.addEventListener('click', setMonthly);
}

var principal = document.getElementById("principal");
var interestRate = document.getElementById("interestRate");
var monthlyPayment = document.getElementById("monthlyPayment");

function setPrincipal() {
	principal.value = this.value;
}

function setInterest() {
	interestRate.value = this.value;
}

function setMonthly() {
	monthlyPayment.value = this.value;
}