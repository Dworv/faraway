public class Color {
    private double r;
    private double g;
    private double b;

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color(double all) {
        r = all;
        b = all;
        g = all;
    }

    public String toString() {
        return "Color(" + r + ", " + g + ", " + b + ")";
    }

    public Color add(Color other) {
        return new Color(r + other.r, g + other.g, b + other.b);
    }

    public Color sub(Color other) {
        return new Color(r - other.r, g - other.g, b - other.b);
    }

    public Color mul(Color other) {
        return new Color(r * other.r, g * other.g, b * other.b);
    }

    public Color mul(double other) {
        return new Color(r * other, g * other, b * other);
    }

    public Color div(Color other) {
        return new Color(r / other.r, g / other.g, b / other.b);
    }

    public Color div(double other) {
        return new Color(r / other, g / other, b / other);
    }

    public Color pow(Color other) {
        return new Color(Math.pow(r, other.r), Math.pow(g, other.g), Math.pow(b, other.b));
    }

    public Color pow(double other) {
        return new Color(Math.pow(r, other), Math.pow(g, other), Math.pow(b, other));
    }

    public Color cap() {
        return new Color(
            Math.max(0, Math.min(1, r)),
            Math.max(0, Math.min(1, g)),
            Math.max(0, Math.min(1, b))
        );
    }

    public int encode() {
        int encoded = 0;
        encoded += 255;
        encoded <<= 8;
        encoded += (int) (r * 255.);
        encoded <<= 8;
        encoded += (int) (g * 255.);
        encoded <<= 8;
        encoded += (int) (b * 255.);
        return encoded;
    }
}