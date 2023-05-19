public class Camera {
    private Vec3 position;
    private Vec3 dir;
    private CameraSettings settings;

    public Camera(Vec3 dir, Vec3 position) {
        this.dir = dir;
        this.position = position;
        this.settings = new CameraSettings();
    }

    public CameraSettings getSettings() {
        return settings;
    }

    public Vec3[][] render(Triangle[] triangles, Vec3 background) {
        int pixelHeight = (int) (settings.getHeight() * 1000);
        int pixelWidth = (int) (settings.getWidth() * 1000);
        Vec3[][] pixels = new Vec3[pixelHeight][pixelWidth];

        for (int i=0; i<pixelHeight; i++) {
            for (int j=0; j<pixelWidth; j++) {
                Ray baseRay = generateBaseRay(i, j, pixelHeight, pixelWidth);
                Vec3 color = new Vec3(0.);
                for (int k=0; k<settings.getSamples(); k++) {
                    Ray aaRay = generateAARay(baseRay, settings.getWidth() / pixelWidth, settings.getHeight() / pixelHeight);
                    Vec3 rayColor = getRayColor(aaRay, triangles, background);
                    color = color.add(rayColor);
                }
                color = color.div(new Vec3(settings.getSamples()));
                pixels[i][j] = color;
            }
        }

        return pixels;
    }

    private Ray generateBaseRay(int i, int j, int ph, int pw) {
        return new Ray(
            new Vec3(
                dir.getX() + (double)(j-pw/2) / (pw/settings.getWidth()),
                dir.getY() - (double)(i-ph/2) / (ph/settings.getHeight()),
                1.
            ),
            position
        );
    }

    private Ray generateAARay(Ray base, double ah, double av) {
        return new Ray(
            base.getDir().add(
                new Vec3(
                    Math.random() * ah,
                    Math.random() * av,
                    0.
                )
            )
        );
    }

    private Vec3 getRayColor(Ray ray, Triangle[] triangles, Vec3 background) {
        double closestDist = Double.POSITIVE_INFINITY;
        Triangle closestTriangle = null;

        for (Triangle t : triangles) {
            Vec4 plane = t.toPlane();
            Vec3 intercept = ray.intercept(plane);
            if (intercept != null) {
                double distance = position.distance(intercept);
                if (distance < closestDist && t.containsPoint(intercept)) {
                    closestDist = distance;
                    closestTriangle = t;
                }
            }
        }

        Vec3 color;
        if (closestTriangle != null) {
            color = closestTriangle.getColor();
        }
        else {
            color = background;
        }

        return color;
    }
}
