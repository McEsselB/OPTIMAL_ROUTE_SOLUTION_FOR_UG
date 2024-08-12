package app;

public class Destination {
    private String name;

    public Destination(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Destination) {
            Destination other = (Destination) obj;
            return other.getName().equals(getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
