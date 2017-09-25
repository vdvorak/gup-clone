package ua.com.gup.model.bank;

import java.util.List;

/**
 * @author Kobylyatskyy Alexander
 */
public class FinanceInfo {
    private long balance;
    private long bonusBalance;
    private List<InternalTransaction> internalTransactionList;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(long bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public List<InternalTransaction> getInternalTransactionList() {
        return internalTransactionList;
    }

    public void setInternalTransactionList(List<InternalTransaction> internalTransactionList) {
        this.internalTransactionList = internalTransactionList;
    }

    @Override
    public String toString() {
        return "FinanceInfo{" +
                "balance=" + balance +
                ", bonusBalance=" + bonusBalance +
                ", internalTransactionList=" + internalTransactionList +
                '}';
    }
}
