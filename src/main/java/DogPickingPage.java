public class DogPickingPage {
    private DogPicking dogPicking;

    public DogPickingPage(DogPicking dogPicking) {
        this.dogPicking = dogPicking;
    }

    public void enterTtile(String title) {
        dogPicking.setTitle(title);
    }
}
