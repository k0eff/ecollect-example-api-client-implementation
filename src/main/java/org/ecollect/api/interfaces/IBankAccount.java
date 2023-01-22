package org.ecollect.api.interfaces;

import org.ecollect.api.classes.BankAccount;
import org.ecollect.api.classes.BankAccountAccNumber;
import org.ecollect.api.classes.BankAccountIBAN;

public interface IBankAccount {
//    public String getAccount_holder();
//    public IBankAccount setAccount_holder(String account_holder);

    public BankAccountAccNumber getBankAccountAccNumber();
    public BankAccountIBAN getBankAccountIBAN();
    public BankAccount.bankAccountTypeEnum getBankAccountType();
}
