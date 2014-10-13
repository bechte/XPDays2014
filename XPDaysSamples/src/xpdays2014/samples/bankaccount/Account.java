package xpdays2014.samples.bankaccount;

public class Account {
  private double balance = 0.0;
  private double interestRate = Bank.currentInterestRate;

  public double getBalance() {
    return balance;
  }

  public double getInterestRate() {
    return interestRate;
  }
}