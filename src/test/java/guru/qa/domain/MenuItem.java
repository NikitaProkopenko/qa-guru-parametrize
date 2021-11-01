package guru.qa.domain;

public enum MenuItem {
    ALL("All"),
    IMAGES("Images");

    private String desc;

    MenuItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
