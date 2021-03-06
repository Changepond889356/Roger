package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.Shippers;
import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;
//import bsh.This;

public class ActivateShippers_TC003 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "ActivateShippers_TC003";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {
				// customize columns
				bResult = Shippers.customizeShippergrid(sTestCaseID);
				if (bResult == true) {
					//add active shipper
					bResult = Shippers.addShipper(sTestCaseID);
					if (bResult == true) {
						//view shipper
						bResult = Shippers.ShipperWebTable(5, sTestCaseID);
						if (bResult == true) {
							//inactive shipper
							bResult = Shippers.ShipperWebTable(6, sTestCaseID);
							if (bResult == true) {
								//

								bResult=TestActions.LogOut();
								bResult=TestActions.Login_GlobalAdmin();
								bResult = Loads.customizeAGgrid(sTestCaseID);
								if (bResult == true) {
									//add load using inactive shipper
									bResult = Loads.addNewLoad(sTestCaseID);
									if (bResult == true) {
										//check if load created with inactive shipper
										bResult = Loads.LoadsWebTable(17, sTestCaseID);
										if (bResult == false) {

											//sActualResult = "Shipper activated successfully";
											//view inactive shipper
											bResult = Shippers.ShipperWebTable(7, sTestCaseID);
											if (bResult == true) {
												//activate shipper
												bResult = Shippers.ShipperWebTable(8, sTestCaseID);
												if (bResult == true) {
													//view active shipper
													bResult = Shippers.ShipperWebTable(9, sTestCaseID);
													if (bResult == true) {
														if (bResult == true) {
															bResult=TestActions.LogOut();
															bResult=TestActions.Login_GlobalAdmin();
															bResult = Loads.customizeAGgrid(sTestCaseID);
															if (bResult == true) {
																//addd load with active shipper
																bResult = Loads.addNewLoad(sTestCaseID);
																if (bResult == true) {
																	//view load with active shipper
																	bResult = Loads.LoadsWebTable(17, sTestCaseID);
																	if (bResult == true) {

																		sActualResult = "Shipper activated successfully";
																	}
																}
															}

														}

														//sActualResult = "Shipper added successfully";
													}
												}

											}

										}
									}

								}
															}

						}

					}
				}

			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}

		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();
		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
		// ResultComparision();

	}
}
