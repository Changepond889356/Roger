package TestCases;

import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.TestActions;
import AppModules.Users;
import PageObjects.LoadsPage;
import Utils.TestDataImport;
//import junit.framework.Assert;
import Utils.GenericSkins;
import Utils.SetUp;

public class Loads_TC002 extends SetUp {
	@SuppressWarnings("deprecation")
	@Test
	public void main() {
		test = extent.createTest(" Loads_TC002 ");
		boolean bResult = false;
		// String expected = "Uploaded Document";
		String sActTestCaseID = "Loads_TC002";
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sActTestCaseID);
			if (bResult == true) {
				bResult = Loads.customizeAGgrid(sActTestCaseID);
			}

			if (bResult == true) {
				bResult = false;
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					// customize AG grid
					// sTestStepData = "Load Date;Shipper;Shipper Contact;Carrier
					// Name;Status;Origin;Destination;Rate;Rate UOM;Commodity";
					// Loads.customizeAGgrid(sActTestCaseID);

					// Search for record in AG grid
					// sTestStepData = "Current Date;CP Shipper;NA;Changepond
					// T;Open;NA;NA;0.25;Bushels;Corn";
					bResult = Loads.LoadsWebTable(4, sActTestCaseID);
					if (bResult == true) {
						Loads.uploadDoc("Origin Ticket");
						Loads.uploadDoc("Dest. Ticket");
						bResult=Loads.editLoad(sActTestCaseID);
						bResult = Loads.LoadsWebTable(5, sActTestCaseID);
						

					}
					LoadsPage.eDelete().click();
					

				}
			}

		} catch (Exception error) {
			bResult = false;

		}
		driver.close();
		driver.quit();
		/*
		 * if (iTotalTestStepsFailed > 0) { SRegPackTestCaseStatus = "Failed"; } else {
		 * SRegPackTestCaseStatus="Passed"; } System.out.println("expected:" +
		 * sTestCaseExpectedResult); System.out.println("Actual Result:" +
		 * sActualResult);
		 */// Assert.assertEquals(sTestCaseExpectedResult.trim().toUpperCase(),
		// sActualResult.toUpperCase().trim());

	}
}