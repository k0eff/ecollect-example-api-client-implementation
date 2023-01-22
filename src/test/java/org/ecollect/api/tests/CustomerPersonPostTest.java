package org.ecollect.api.tests;

import com.neovisionaries.i18n.CountryCode;
import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.CustomerPersonTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomerPersonPostTest {

	private EcollectAPIHandler api;
	@Before
	public void setup() throws Exception {
		api = ApiHandlerHelper.setupApi();

		bankAccountIBANComparable = CustomerPersonTestHelper.createBankAccountIban();
		bankAccountAccNumberComparable = CustomerPersonTestHelper.createBankAccountAccNumber();
		customerMetadataComparable = CustomerPersonTestHelper.createCustomerMetadata();
		customerEventComaparable = CustomerPersonTestHelper.createCustomerEvent();

		customer = CustomerPersonTestHelper.CustomerObjCreate(bankAccountIBANComparable, bankAccountAccNumberComparable, customerMetadataComparable, customerEventComaparable);
		customerPosted = CustomerPersonTestHelper.postCustomer(this.customer, api);
	}

	private Customer customer;
	private CustomerMetadata customerMetadataComparable;
	private CustomerEvent customerEventComaparable;
	private BankAccountIBAN bankAccountIBANComparable;
	private BankAccountAccNumber bankAccountAccNumberComparable;
	private Customer customerPosted;




	@Test
	public void CustomerObjMetadataCheck() {
		Assert.assertFalse(this.customer.getMetadata().isEmpty());
		Assert.assertTrue(this.customer.getMetadata().contains(customerMetadataComparable));
		Assert.assertTrue(this.customer.getEvents().contains(customerEventComaparable));
	}

	@Test
	public void CustomerObjEventsCheck() {
		Assert.assertFalse(this.customer.getEvents().isEmpty());
		Assert.assertTrue(this.customer.getEvents().contains(customerEventComaparable));
	}


	@Test
	public void CustomerObjPersonSexCheck() {
		Assert.assertFalse(this.customer.getEvents().isEmpty());
		Assert.assertTrue(this.customer.getEntity().getEntityType().equals("Person"));
		Assert.assertTrue(this.customer.getEntity().getPerson().getSex().equals(Person.SexEnum.M));
	}

	@Test
	public void CustomerObjBankAccountsCheck() {
		Assert.assertFalse(this.customer.getEvents().isEmpty());
		ArrayList<BankAccount> ba = this.customer.getEntity().getBank_accounts();
		for (BankAccount baSingle : ba) {
			if (baSingle.getBankAccountType().equals(BankAccount.bankAccountTypeEnum.IBAN)) {
				Assert.assertTrue(baSingle.getBankAccountIBAN().getBicswift().equals(bankAccountIBANComparable.getBicswift()));
				Assert.assertTrue(baSingle.getBankAccountIBAN().getIban().equals(bankAccountIBANComparable.getIban()));
				Assert.assertTrue(baSingle.getBankAccountIBAN().getAccount_holder().equals(bankAccountIBANComparable.getAccount_holder()));
			}
			if (baSingle.getBankAccountType().equals(BankAccount.bankAccountTypeEnum.AccNumber)) {
				Assert.assertTrue(baSingle.getBankAccountAccNumber().getClearing_number().equals(bankAccountAccNumberComparable.getClearing_number()));
				Assert.assertTrue(baSingle.getBankAccountAccNumber().getAccount_holder().equals(bankAccountAccNumberComparable.getAccount_holder()));
				Assert.assertTrue(baSingle.getBankAccountAccNumber().getBank_country().equals(bankAccountAccNumberComparable.getBank_country()));
			}
		}
	}

	/**
	 * The following tests are for the  generated Customer from the api using the following code
	 *
	 * 				EcollectAPIHandler api = new EcollectAPIHandler(authToken, urlString, 10);
	 * 				Customer customerPosted = api.customer.postCustomer(customer);
	 * 				this.customerPosted = customerPosted;
	 *
	 */

	@Test
	public void CustomerPostedObjMetadataCheck() {
		Assert.assertFalse(this.customerPosted.getMetadata().isEmpty());

		ArrayList<CustomerMetadata> cmL = this.customerPosted.getMetadata();
		int counter = 0;

		for (CustomerMetadata cmSingle : cmL) {
			if (cmSingle.getType().equals(customerMetadataComparable.getType())
					&& cmSingle.getValue().equals(customerMetadataComparable.getValue())
				) counter++; // because 2 metadata properties are added, but only 1 equals to this data
		}
		Assert.assertTrue(counter==1);
	}

	@Test
	public void CustomerPostedObjEventsCheck() {
		Assert.assertFalse(this.customerPosted.getEvents().isEmpty());

		ArrayList<CustomerEvent> ceL = this.customerPosted.getEvents();
		for (CustomerEvent ceSingle : ceL) {
			Assert.assertTrue(ceSingle.getDescription().equals(customerEventComaparable.getDescription()));
			Assert.assertTrue(ceSingle.getLocation().equals(customerEventComaparable.getLocation()));
			Assert.assertTrue(ceSingle.getOccurrence().equals(customerEventComaparable.getOccurrence()));
			Assert.assertTrue(ceSingle.getTitle().equals(customerEventComaparable.getTitle()));
			Assert.assertTrue(ceSingle.getYour_reference().equals(customerEventComaparable.getYour_reference()));
		}
	}


	@Test
	public void CustomerPostedObjPersonSexCheck() {
		Assert.assertFalse(this.customerPosted.getEvents().isEmpty());
		Assert.assertTrue(this.customerPosted.getEntity().getEntityType().equals("Person"));
		Assert.assertTrue(this.customerPosted.getEntity().getPerson().getSex().equals(Person.SexEnum.M));
	}

	@Test
	public void CustomerPostedObjBankAccountsCheck() {
		ArrayList<BankAccount> ba = this.customerPosted.getEntity().getBank_accounts();
		for (BankAccount baSingle : ba) {
			if (baSingle.getBankAccountType().equals(BankAccount.bankAccountTypeEnum.IBAN) ) {
				Assert.assertTrue(baSingle.getBankAccountIBAN().getBicswift().equals(bankAccountIBANComparable.getBicswift()));
				Assert.assertTrue(baSingle.getBankAccountIBAN().getIban().equals(bankAccountIBANComparable.getIban()));
				Assert.assertTrue(baSingle.getBankAccountIBAN().getAccount_holder().equals(bankAccountIBANComparable.getAccount_holder()));
			}
			if (baSingle.getBankAccountType().equals(BankAccount.bankAccountTypeEnum.AccNumber) ) {
				Assert.assertTrue(baSingle.getBankAccountAccNumber().getClearing_number().equals(bankAccountAccNumberComparable.getClearing_number()));
				Assert.assertTrue(baSingle.getBankAccountAccNumber().getAccount_holder().equals(bankAccountAccNumberComparable.getAccount_holder()));
				Assert.assertTrue(baSingle.getBankAccountAccNumber().getBank_country().equals(bankAccountAccNumberComparable.getBank_country()));
			}
		}
	}



}
