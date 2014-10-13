package xpdays2014.samples.bankaccount.defaultrunner;

import org.junit.*;
import xpdays2014.samples.bankaccount.Account;
import xpdays2014.samples.bankaccount.Bank;

import static org.junit.Assert.assertEquals;

public class BankAccountTest {
  private Account account;
  @Before
  public void setCurrentInterestRate() {
    Bank.currentInterestRate = DEFAULT_RATE;
    account = new Account();
  }

  @Test
  public void givenNewAccount_balanceIsZero() throws Exception {
    assertMoneyEquals(0.0, account.getBalance());
  }

  @Test
  public void givenNewAccount_interestRateIsSet() throws Exception {
    assertMoneyEquals(DEFAULT_RATE, account.getInterestRate());
  }

  @Test
  public void givenNewAccount_whenInterestRateChanges_shouldHaveOldInterestRate() throws Exception {
    Bank.currentInterestRate = CHANGED_RATE;
    assertMoneyEquals(DEFAULT_RATE, account.getInterestRate());
  }

  @Test
  public void whenInterestRateChanges_newAccountShouldHaveNewInterestRate() throws Exception {
    Bank.currentInterestRate = CHANGED_RATE;
    Account newAccount = new Account();
    assertMoneyEquals(CHANGED_RATE, newAccount.getInterestRate());
  }

  private static final double DEFAULT_RATE = 2.75;
  private static final double CHANGED_RATE = 3.25;
  private static final double MONEY_DELTA = .00001;

  private static void assertMoneyEquals(double expected, double actual) {
    assertEquals(expected, actual, MONEY_DELTA);
  }
}