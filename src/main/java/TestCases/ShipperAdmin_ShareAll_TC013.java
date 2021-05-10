package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class ShipperAdmin_ShareAll_TC013 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "ShipperAdmin_ShareAll_TC013";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {
				//handle newly created deal widget
				Deals.DealWidget(sTestCaseID);
				//share to contact
				bResult=Share.ShareDeal(sTestCaseID);
				if(bResult==true)
				{
					bResult=false;
					TestActions.LogOut();
					//login as carrier user
					bResult = TestActions.Login("ShipperAdmin_ShareAll_TC013(2)");
					if(bResult==true)
					{
						bResult=false;
						//check shared deal
						bResult=Deals.DealWidget("ShipperAdmin_ShareAll_TC013(2)");
						if(bResult==true)
						{
							TestActions.LogOut();
							sActualResult = "Deal has been shared";
						}
						
					}
				}
				
				//sActualResult="Deal shared successfully";
			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}

		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
// ResultComparision();

	}
}
