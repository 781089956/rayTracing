package top.yzzblog.rayTracing;

public class Vec3Util {

    public static Vec3 randomInUnitSphere() {
        Vec3 p;
        do {
            p =new Vec3(
                    (float)(Math.random()),
                    (float)(Math.random()),
                    (float)(Math.random())).scale(2.0f).subtract(new Vec3(1.0f, 1.0f, 1.0f));
        } while (p.length() >= 1);

        return p;

    }
}
