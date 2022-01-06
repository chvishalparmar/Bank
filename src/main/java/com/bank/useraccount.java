package com.bank;

public class useraccount {
private String userid,transferto;
private double transfer,balance,deposit,withdrawal;
/**
 * @return the userid
 */
String getUserid() {
	return userid;
}
/**
 * @param userid the userid to set
 */
void setUserid(String userid) {
	this.userid = userid;
}
/**
 * @return the transferto
 */
String getTransferto() {
	return transferto;
}
/**
 * @param transferto the transferto to set
 */
void setTransferto(String transferto) {
	this.transferto = transferto;
}
/**
 * @return the transfer
 */
double getTransfer() {
	return transfer;
}
/**
 * @param transfer the transfer to set
 */
boolean setTransfer(String transfer) {
	double tr = Double.valueOf(transfer);
	this.transfer = tr;
	boolean check=false;
	if(this.balance > tr)
	{
	    this.balance = this.balance - tr;
	    check = true;
	    return check;
	}
	else
		return check;
}
/**
 * @return the balance
 */
double getBalance() {
	return balance;
}
/**
 * @param balance the balance to set
 */
void setBalance(double balance) {
	this.balance = balance;
}
/**
 * @return the deposit
 */
double getDeposit() {
	return deposit;
}
/**
 * @param deposit the deposit to set
 */
void setDeposit(double deposit) {
	this.deposit = deposit;
	this.balance = this.balance + deposit;
}
/**
 * @return the withdrawal
 */
double getWithdrawal() {
	return withdrawal;
}
/**
 * @param withdrawal the withdrawal to set
 */
boolean setWithdrawal(String withdrawal) {
	double wi = Double.valueOf(withdrawal);
	this.withdrawal = wi;
	boolean check=false;
	if(this.balance > wi)
	{
		this.balance = this.balance - wi;
		check = true;
		return check;
	}
	else
		return check;
}
public useraccount(String userid) {
	super();
	this.userid = userid;
}
}
