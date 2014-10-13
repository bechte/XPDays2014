package xpdays2014.samples.bankaccount.hierarchicalcontextrunner;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import xpdays2014.samples.bankaccount.Account;
import xpdays2014.samples.bankaccount.Bank;

import static org.junit.Assert.assertEquals;

@RunWith(HierarchicalContextRunner.class)
public class BankAccountTest {
  public class BankContext {
    @Before
    public void setCurrentInterestRate() {
      Bank.currentInterestRate = DEFAULT_RATE;
    }

    public class NewAccountContext {
      private Account newAccount;

      @Before
      public void createNewAccount() throws Exception {
        newAccount = new Account();
      }

      @Test
      public void balanceIsZero() throws Exception {
        assertMoneyEquals(0.0, newAccount.getBalance());
      }

      @Test
      public void interestRateIsSet() throws Exception {
        assertMoneyEquals(DEFAULT_RATE, newAccount.getInterestRate());
      }
    }

    public class OldAccountContext {
      private Account oldAccount;

      @Before
      public void createOldAccount() throws Exception {
        oldAccount = new Account();
      }

      public class AfterInterestRateChangeContext {
        @Before
        public void changeInterestRate() {
          Bank.currentInterestRate = CHANGED_RATE;
        }

        @Test
        public void shouldHaveOldInterestRate() throws Exception {
          assertMoneyEquals(DEFAULT_RATE, oldAccount.getInterestRate());
        }

        @Test
        public void newAccountShouldHaveNewInterestRate() throws Exception {
          Account newAccount = new Account();
          assertMoneyEquals(CHANGED_RATE, newAccount.getInterestRate());
        }
      }
    }
  }

  private static final double DEFAULT_RATE = 2.75;
  private static final double CHANGED_RATE = 3.25;
  private static final double MONEY_DELTA = .00001;

  private static void assertMoneyEquals(double expected, double actual) {
    assertEquals(expected, actual, MONEY_DELTA);
  }
}