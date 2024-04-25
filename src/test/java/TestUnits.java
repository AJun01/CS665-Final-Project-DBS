import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUnits {
    @Test
    public void testPage1() {
        DogBoardingCampDatabase dogBoardingCampDatabase = new DogBoardingCampDatabase();
        DogBoardingCampDatabasePage dogBoardingCampDatabasePage = new DogBoardingCampDatabasePage(dogBoardingCampDatabase);
        dogBoardingCampDatabasePage.enterTtile("wakawaka");
        assertEquals("wakawaka", dogBoardingCampDatabase.getTitle());

    }
    @Test
    public void testPage2() {
        DogDataTable dogDataTable = new DogDataTable();
        DogDataTablePage dogDataTablePage = new DogDataTablePage(dogDataTable);
        dogDataTablePage.enterTtile("takitaki");
        assertEquals("takitaki", dogDataTable.getTitle());
    }
    @Test
    public void testPage3() {
        DogPicking dogPicking = new DogPicking();
        DogPickingPage dogPickingPage = new DogPickingPage(dogPicking);
        dogPickingPage.enterTtile("otot");
        assertEquals("otot", dogPicking.getTitle());
    }
}
