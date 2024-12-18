package componnet;

public enum UserTypes {
    ADMIN("ADMIN"),
    STUDENT("STUDENT"),
    INSTRUCTOR("INSTRUCTOR");

    private final String value;

    UserTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
