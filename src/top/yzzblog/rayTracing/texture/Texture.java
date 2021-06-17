package top.yzzblog.rayTracing.texture;

import top.yzzblog.rayTracing.Vec3;

public abstract class Texture {
    private Vec3 color;

    public Texture(Vec3 color) {
        this.color = color;
    }
}
