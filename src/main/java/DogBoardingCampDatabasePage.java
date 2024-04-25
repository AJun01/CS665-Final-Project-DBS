public class DogBoardingCampDatabasePage {
    private DogBoardingCampDatabase dogBoardingCampDatabase;

    public DogBoardingCampDatabasePage(DogBoardingCampDatabase dogBoardingCampDatabase) {
        this.dogBoardingCampDatabase = dogBoardingCampDatabase;
    }

    public void enterTtile(String title) {
        dogBoardingCampDatabase.setTitle(title);
    }
}


