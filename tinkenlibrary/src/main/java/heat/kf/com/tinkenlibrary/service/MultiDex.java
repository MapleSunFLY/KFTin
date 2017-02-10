package heat.kf.com.tinkenlibrary.service;

import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/1/9.
 */
public class MultiDex {

    public static void expandFieldArray(Object instance, String fieldName, Object[] extraElements)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // 得到Element数组字段
        Field jlrField = findField(instance, fieldName);
        // 得到pathList对象里面的dexElements数组
        Object[] original = (Object[]) jlrField.get(instance);
        // 创建一个新的数组用来存放合并之后的结果
        Object[] combined = (Object[]) Array.newInstance(original.getClass().getComponentType(), original.length + extraElements.length);
        // 创建一个新的数组用来存放合并之后的结果
        System.arraycopy(extraElements, 0, combined, 0, extraElements.length);
        // 将其他dex的Elements数组复制到创建的数组中去
        System.arraycopy(original, 0, combined, extraElements.length, original.length);
        // 将得到的这个合并的新数组的值设置到pathList对象的Element数组字段上
        jlrField.set(instance, combined);
        Log.d("FLY","合并完成: "+combined.length);
    }

    //使用反射获取instance这个类，的name这个属性
    public static Field findField(Object instance, String name) throws NoSuchFieldException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(name);

                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                return field;
            } catch (NoSuchFieldException e) {
                // ignore and search next
            }
        }

        throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
    }

    public static Field findField(Class<?> originClazz, String name) throws NoSuchFieldException {
        for (Class<?> clazz = originClazz; clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(name);

                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                // ignore and search next
            }
        }

        throw new NoSuchFieldException("Field " + name + " not found in " + originClazz);
    }
    //使用反射获取instance这个类，name方法，后面是参数，Class<?>... parameterTypes ==>这种写法是这个类的方法不确定，传入一个数组，可以有一到多个参数
    public static Method  findMethod(Object instance, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                //获取域的字段
                Method method = clazz.getDeclaredMethod(name, parameterTypes);

                if (!method.isAccessible()) {
                    //设置访问权限
                    method.setAccessible(true);
                }

                return method;
            } catch (NoSuchMethodException e) {
                // ignore and search next
            }
        }

        throw new NoSuchMethodException("Method "
                + name
                + " with parameters "
                + Arrays.asList(parameterTypes)
                + " not found in " + instance.getClass());
    }
}
