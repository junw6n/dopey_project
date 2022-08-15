package domain;

public enum TodoStatus {
    IDLE(0), IN_PROGRESS(1), COMPLETE(2);

    private final int value;

    TodoStatus(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    public static TodoStatus valueOf(int value) {
        switch (value) {
            case 0: return IDLE;
            case 1: return IN_PROGRESS;
            case 2: return COMPLETE;
            default: throw new AssertionError("Unknown value: " + value);
        }
    }
}
