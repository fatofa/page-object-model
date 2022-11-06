package core;

public class BasePage {

    protected KeywordWeb keyword;

    public BasePage() {
        keyword = new KeywordWeb();
    }

    public BasePage(KeywordWeb keyword) {
        this.keyword = keyword;
    }



}
