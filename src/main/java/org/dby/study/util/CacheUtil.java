package org.dby.study.util;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JVM本地缓存工具类
 *
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class CacheUtil {
    /**
     * 缓存表对象
     */
    private static ConcurrentHashMap<String, Cache> caches = new ConcurrentHashMap<>();

    /**
     * * 添加缓存
     *
     * @param key             键值
     * @param value           缓存值
     * @param expireTimeStamp 过期时间戳
     */
    public static void put(String key, Object value, long expireTimeStamp) {
        if (key != null && value != null && expireTimeStamp > System.currentTimeMillis()) {
            caches.put(key, Cache.createCache(value, expireTimeStamp));
        }
    }

    /**
     * 获取缓存
     *
     * @param key 缓存键值
     * @return 缓存值
     */
    public static Object get(String key) {
        Cache cache = caches.get(key);
        if (cache != null) {
            if (cache.getExpireTimeStamp() < System.currentTimeMillis()) {
                caches.remove(key);
                return null;
            }
            WeakReference value = cache.getValue();
            if (value != null) {
                return value.get();
            }
        }
        return null;
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void remove(String key) {
        caches.remove(key);
    }

    /**
     * 刷新缓存
     */
    public static void refresh() {
        for (String key : caches.keySet()) {
            Cache cache = caches.get(key);
            if (cache != null) {
                if (cache.getExpireTimeStamp() < System.currentTimeMillis()) {
                    caches.remove(key);
                }
            }
        }
    }

    /**
     * 缓存对象
     */
    private static class Cache {
        private Cache() {
        }

        /**
         * 创建一个缓存对象
         *
         * @param value           值
         * @param expireTimeStamp 过期时间
         * @return
         */
        private static Cache createCache(Object value, long expireTimeStamp) {
            Cache cache = new Cache();
            cache.setValue(new WeakReference(value));
            cache.setExpireTimeStamp(expireTimeStamp);
            return cache;
        }

        /**
         * 缓存值(WeakReference保持的对象会在发生gc时被自动回收)
         */
        private WeakReference<Object> value;
        /**
         * 过期时间
         */
        private long expireTimeStamp;

        /**
         * Getter method for property <tt>value</tt>.
         *
         * @return property value of value
         */
        public WeakReference getValue() {
            return value;
        }

        /**
         * Setter method for property <tt>value</tt>.
         *
         * @param value value to be assigned to property value
         */
        public void setValue(WeakReference<Object> value) {
            this.value = value;
        }

        /**
         * Getter method for property <tt>expireTimeStamp</tt>.
         *
         * @return property value of expireTimeStamp
         */
        public long getExpireTimeStamp() {
            return expireTimeStamp;
        }

        /**
         * Setter method for property <tt>expireTimeStamp</tt>.
         *
         * @param expireTimeStamp value to be assigned to property expireTimeStamp
         */
        public void setExpireTimeStamp(long expireTimeStamp) {
            this.expireTimeStamp = expireTimeStamp;
        }
    }
}