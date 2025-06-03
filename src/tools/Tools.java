package tools;

public enum Tools {
    PEN("pen"),
    RUBBER("rubber"),
    RECT("rect"),
    CLEAR("clear");

    private final String label;

    Tools(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
