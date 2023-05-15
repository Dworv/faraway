public class App {
    public static void main(String[] args) throws Exception {
        Triangle t1 = new Triangle(
            new Vec3(-1., 0., 2.), 
            new Vec3(0., -1., 3.), 
            new Vec3(1., 0., 2.),
            new Vec3(1., 0., 0.)
        );

        Triangle t2 = new Triangle(
            new Vec3(-1., -1., 2.), 
            new Vec3(1., -1., 2.5), 
            new Vec3(1., 1., 2.5),
            new Vec3(0., 1., 0.)
        );

    Triangle[] triangles = { t1, t2 };

        Camera c = new Camera(
            new Vec3(0., 0., 1.),
            new Vec3(0.)
        );

        Vec3[][] pixels = c.render(triangles, 1000, 1000);
        // for (Vec3[] row : pixels) {
        //     for (Vec3 color : row) {
        //         System.out.println(color);
        //     }
        //     System.out.println();
        // }
        Draw.drawImage(pixels);
    }
}
