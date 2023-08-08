package rpc.network.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 序列化工具类
 */
public class SerializationUtil {
    private static Map<Class<?>, Schema> cacheSchema = new ConcurrentHashMap<>();


    /**
     * 将对象序列化成二进制
     */
    public static byte[] serialObject(Object object) {
        Class<?> aClass = object.getClass();

        Schema schema = cacheSchema.get(aClass);

        if (schema == null) {
            schema = RuntimeSchema.getSchema(aClass);
            cacheSchema.put(aClass, schema);
        }

        LinkedBuffer buffer = LinkedBuffer.allocate();

        byte[] result = new byte[]{};

        try {
            result = ProtostuffIOUtil.toByteArray(object, schema, buffer);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        return result;
    }


    /**
     * 将二进制反序列化成Java对象
     */

    public static Object deSerialObject(Class<?> clazz, byte[] data) {

        Schema schema = cacheSchema.get(clazz);

        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            cacheSchema.put(clazz, schema);
        }

        Object result = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, result, schema);

        return result;
    }


}
