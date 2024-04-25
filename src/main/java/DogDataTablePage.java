public class DogDataTablePage {
    private DogDataTable dogDataTable;

    public DogDataTablePage(DogDataTable dogDataTable) {
        this.dogDataTable = dogDataTable;
    }

    public void enterTtile(String title) {
        dogDataTable.setTitle(title);
    }
}
