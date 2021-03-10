package utils;

public enum ListOfIdsForGetTests {
    VALID_ID ("veownGF"),
    EMPTY_ID (""),
    NOT_EXISTING_ID ("2123820");

    public String imageId;

    ListOfIdsForGetTests(String imageId) {
        this.imageId = imageId;
    }
}
