document.body.textContent = "";

var header = document.createElement('h1');
header.textContent = "This page has been eaten";
document.body.appendChild(header);
var customerName = prompt("Please enter your name", "<name goes here>");