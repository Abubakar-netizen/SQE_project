package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;

/**
 * RedisHelper - Utility for Redis operations
 */
public class RedisHelper {
    private static JedisPool jedisPool;

    static {
        try {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(10);
            poolConfig.setMaxIdle(5);
            poolConfig.setMinIdle(1);

            jedisPool = new JedisPool(
                    poolConfig,
                    ConfigReader.getRedisHost(),
                    ConfigReader.getRedisPort());
        } catch (Exception e) {
            System.err.println("Warning: Redis connection failed. Redis tests will be skipped.");
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Get value for a key
     * 
     * @param key Redis key
     * @return Value or null if not found
     */
    public static String getValue(String key) {
        if (jedisPool == null)
            return null;

        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            System.err.println("Redis get failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Set key-value pair
     * 
     * @param key   Redis key
     * @param value Value to set
     */
    public static void setValue(String key, String value) {
        if (jedisPool == null)
            return;

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        } catch (Exception e) {
            System.err.println("Redis set failed: " + e.getMessage());
        }
    }

    /**
     * Set key-value pair with expiration
     * 
     * @param key     Redis key
     * @param value   Value to set
     * @param seconds Expiration time in seconds
     */
    public static void setValueWithExpiry(String key, String value, int seconds) {
        if (jedisPool == null)
            return;

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            System.err.println("Redis setex failed: " + e.getMessage());
        }
    }

    /**
     * Delete a key
     * 
     * @param key Redis key to delete
     */
    public static void deleteKey(String key) {
        if (jedisPool == null)
            return;

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        } catch (Exception e) {
            System.err.println("Redis delete failed: " + e.getMessage());
        }
    }

    /**
     * Check if key exists
     * 
     * @param key Redis key
     * @return true if key exists
     */
    public static boolean keyExists(String key) {
        if (jedisPool == null)
            return false;

        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        } catch (Exception e) {
            System.err.println("Redis exists check failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all keys matching pattern
     * 
     * @param pattern Key pattern (e.g., "session:*")
     * @return Set of matching keys
     */
    public static Set<String> getKeys(String pattern) {
        if (jedisPool == null)
            return null;

        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(pattern);
        } catch (Exception e) {
            System.err.println("Redis keys failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Set hash field
     * 
     * @param key   Hash key
     * @param field Field name
     * @param value Field value
     */
    public static void setHashField(String key, String field, String value) {
        if (jedisPool == null)
            return;

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(key, field, value);
        } catch (Exception e) {
            System.err.println("Redis hset failed: " + e.getMessage());
        }
    }

    /**
     * Get hash field value
     * 
     * @param key   Hash key
     * @param field Field name
     * @return Field value
     */
    public static String getHashField(String key, String field) {
        if (jedisPool == null)
            return null;

        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(key, field);
        } catch (Exception e) {
            System.err.println("Redis hget failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Get all hash fields and values
     * 
     * @param key Hash key
     * @return Map of field-value pairs
     */
    public static Map<String, String> getAllHashFields(String key) {
        if (jedisPool == null)
            return null;

        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            System.err.println("Redis hgetAll failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Check if Redis is available
     * 
     * @return true if Redis is available
     */
    public static boolean isAvailable() {
        if (jedisPool == null)
            return false;

        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.ping().equals("PONG");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Close Redis connection pool
     */
    public static void close() {
        if (jedisPool != null && !jedisPool.isClosed()) {
            jedisPool.close();
        }
    }
}
