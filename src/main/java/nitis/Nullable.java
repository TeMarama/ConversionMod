package nitis;

public class Nullable<T> {
    private T itm;
    private boolean isNull = true;

    public Nullable(T value) {
        isNull = value == null;
        itm = value;
    }
    public Nullable() {
        itm = null;
    }
    public @org.jetbrains.annotations.Nullable T getValue() {
        return itm;
    }
    public boolean isNull() {
        return isNull;
    }
    public void setValue(T value) {
        isNull = value == null;
        itm = value;
    }
}
