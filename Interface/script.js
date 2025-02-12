let balance = 100000.0;

function checkBalance() {
    let balanceElement = document.getElementById('balance');
    balanceElement.innerText = `Balance: $${balance.toFixed(2)}`;
    balanceElement.style.display = 'block';
}

function showWithdrawForm() {
    document.getElementById('action-area').innerHTML = `
        <input type='number' id='withdrawAmount' placeholder='Enter amount to withdraw'>
        <button onclick='withdrawMoney()'>Confirm Withdrawal</button>
    `;
}

function withdrawMoney() {
    let amount = parseFloat(document.getElementById('withdrawAmount').value);
    if (isNaN(amount) || amount <= 0) {
        alert("Invalid amount. Please enter a positive value.");
    } else if (amount > balance) {
        alert("Insufficient funds. Your balance is $" + balance.toFixed(2));
    } else {
        balance -= amount;
        checkBalance();
        alert(`You have withdrawn $${amount.toFixed(2)}. Your new balance is $${balance.toFixed(2)}`);
        document.getElementById('action-area').innerHTML = '';
    }
}

function showDepositForm() {
    document.getElementById('action-area').innerHTML = `
        <input type='number' id='depositAmount' placeholder='Enter amount to deposit'>
        <button onclick='depositMoney()'>Confirm Deposit</button>
    `;
}

function depositMoney() {
    let amount = parseFloat(document.getElementById('depositAmount').value);
    if (isNaN(amount) || amount <= 0) {
        alert("Invalid amount. Please enter a positive value.");
    } else {
        balance += amount;
        checkBalance();
        alert(`You have deposited $${amount.toFixed(2)}. Your new balance is $${balance.toFixed(2)}`);
        document.getElementById('action-area').innerHTML = '';
    }
}

function exitATM() {
    alert("Thank you for using the ATM. Goodbye!");
    window.close();
}