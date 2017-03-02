package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	private VendingMachine vm;
	private VendingMachineItem item;
	private final String A_CODE = "A";
	private final String B_CODE = "B";
	private final String C_CODE = "C";
	private final String D_CODE = "D";
	
	@Before
	public void setUp() throws Exception {
		vm = new VendingMachine();
		item = new VendingMachineItem("Dat item", 2.50);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests that adds item adds an item
	 */
	@Test(expected=VendingMachineException.class)
	public void testAddItem() {
		vm.addItem(item, A_CODE);
		assertEquals(item, vm.getItem(VendingMachine.A_CODE));
		vm.addItem(item, B_CODE);
		assertEquals(item, vm.getItem(VendingMachine.B_CODE));
		vm.addItem(item, C_CODE);
		assertEquals(item, vm.getItem(VendingMachine.C_CODE));
		vm.addItem(item, D_CODE);
		assertEquals(item, vm.getItem(VendingMachine.D_CODE));
		vm.addItem(item, "f");
	}

	/**
	 * Tests that removeItems removes an item
	 */
	@Test
	public void testRemoveItem() {
		vm.addItem(item, A_CODE);
		vm.removeItem(A_CODE);
		assertEquals(null, vm.getItem(A_CODE));
	}

	/**
	 * Test that insertMoney adds money to the balance
	 */
	@Test
	public void testInsertMoney() {
		vm.insertMoney(2.50);
		assertEquals(2.50, vm.getBalance(), 0.001);
	}

	/**
	 * Tests the getBalance method
	 */
	@Test
	public void testGetBalance() {
		assertEquals(0, vm.getBalance(), 0.001);
	}

	/**
	 * Tests the makePurchase method
	 */
	@Test
	public void testMakePurchase() {
		vm.addItem(item,VendingMachine.A_CODE);
		vm.insertMoney(3.00);
		assertEquals(true, vm.makePurchase(VendingMachine.A_CODE));
		assertEquals(null, vm.getItem(VendingMachine.A_CODE));
		assertEquals(0.50, vm.getBalance(), 0.001);
	}

	/**
	 * Tests that returnChange method returns the balance and sets it to 0
	 */
	@Test
	public void testReturnChange() {
		vm.insertMoney(3.00);
		assertEquals(3.00, vm.returnChange(), 0.001);
		assertEquals(0, vm.getBalance(), 0.001);
	}
	
	/**
	 * Test that you can't make a purchase if the balance is below the item price
	 */
	@Test
	public void makePurchaseInsufficientBalanceTest() {
		vm.addItem(item,VendingMachine.A_CODE);
		vm.insertMoney(1.00);
		assertEquals(false, vm.makePurchase(VendingMachine.A_CODE));
	}
	
	/**
	 * Test that you can't make a purchase when there is no item
	 */
	@Test
	public void makePurchaseItemExistsTest() {
		assertEquals(false, vm.makePurchase(VendingMachine.A_CODE));
	}

}
