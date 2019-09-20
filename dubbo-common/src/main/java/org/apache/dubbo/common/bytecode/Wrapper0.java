package org.apache.dubbo.common.bytecode;

interface Car {
    String getBrand();
    long getWeight();
    void make(String brand, long weight);
}

/**
 *
 * */
public class Wrapper0 extends Wrapper {
    /** 字段名列表*/
    public static String[] pns;

    /** 字段名与字段类型的映射关系 */
    public static java.util.Map<String, Class<?>> pts;

    /** 方法名列表 */
    public static String[] mns;

    /** 声明的方法名列表 */
    public static String[] dmns;

    /** 每个public方法的参数类型 */
    public static Class[] mts0;
    public static Class[] mts1;
    public static Class[] mts2;

    public String[] getPropertyNames() {
        return pns;
    }

    public boolean hasProperty(String n) {
        //return pts.containsKey($1);
        return false;
    }

    public Class getPropertyType(String n) {
        //return (Class) pts.get($1);
        return null;
    }

    public String[] getMethodNames() {
        return mns;
    }

    public String[] getDeclaredMethodNames() {
        return dmns;
    }

    public void setPropertyValue(Object o, String n, Object v) {
        /*org.apache.dubbo.common.bytecode.Car w;
        try {
            w = ((org.apache.dubbo.common.bytecode.Car) $1);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        throw new org.apache.dubbo.common.bytecode.NoSuchPropertyException("Not found property \"" + $2 + "\" field or setter method in class org.apache.dubbo.common.bytecode.Car.");
    */
        return;
    }

    public Object getPropertyValue(Object o, String n) {
        /*org.apache.dubbo.common.bytecode.Car w;
        try {
            w = ((org.apache.dubbo.common.bytecode.Car) $1);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        if ($2.equals("brand")) {
            return ($w) w.getBrand();
        }
        if ($2.equals("weight")) {
            return ($w) w.getWeight();
        }
        throw new org.apache.dubbo.common.bytecode.NoSuchPropertyException("Not found property \"" + $2 + "\" field or setter method in class org.apache.dubbo.common.bytecode.Car.");
    */
        return null;
    }

    public Object invokeMethod(Object o, String n, Class[] p, Object[] v) throws java.lang.reflect.InvocationTargetException {
        /*org.apache.dubbo.common.bytecode.Car w;
        try {
            w = ((org.apache.dubbo.common.bytecode.Car) $1);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        try {
            if ("make".equals($2) && $3.length == 2) {
                w.make((java.lang.String) $4[0], ((Number) $4[1]).longValue());
                return null;
            }
            if ("getBrand".equals($2) && $3.length == 0) {
                return ($w) w.getBrand();
            }
            if ("getWeight".equals($2) && $3.length == 0) {
                return ($w) w.getWeight();
            }
        } catch (Throwable e) {
            throw new java.lang.reflect.InvocationTargetException(e);
        }
        throw new org.apache.dubbo.common.bytecode.NoSuchMethodException("Not found method \"" + $2 + "\" in class org.apache.dubbo.common.bytecode.Car.");

    */

        return null;
    }
}