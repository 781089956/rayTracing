package top.yzzblog.rayTracing.shape;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Hittable;
import top.yzzblog.rayTracing.Ray;

public class FlipNormal implements Hittable{
    private Hittable shape;
    public FlipNormal(Hittable shape){
        this.shape = shape;
    }
    public HitRecord hit(Ray r, float minT, float maxT){
        HitRecord record;
        if(null != (record = shape.hit(r,minT,maxT))) {
            record.setNormal(record.getNormal().multiply(-1));
        }
        return record;
    }

}
