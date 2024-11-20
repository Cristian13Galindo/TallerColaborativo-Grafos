package co.edu.uptc.model;

public class Connection {
    private Substation source;
    private Substation target;
    private double resistance;

    public Connection(Substation source, Substation target, double resistance) {
        this.source = source;
        this.target = target;
        this.resistance = resistance;
    }

    public Substation getSource() {
        return source;
    }

    public Substation getTarget() {
        return target;
    }

    public double getResistance() {
        return resistance;
    }

    @Override
    public String toString() {
        return source + " -> " + target + " (" + resistance + ")";
    }
}