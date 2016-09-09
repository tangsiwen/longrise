package com.longrise.common.redis;

import java.util.List;
import java.util.Map;

public interface RedisInterface {
	public int TABLE_DEFAULT = 0;
	public int TABLE_ONE = 1;
	public int TABLE_SIX = 6;

	public String get(String key);

	public String get(String key, int index);

	public String set(String key, String value);

	public String set(String key, String value, int index);

	public String set(String key, int expire, String value);

	public String set(String key, int expire, String value, int index);

	public Long delete(String key);

	public Long delete(String key, int index);

	public Long append(String key, String value);

	public Long append(byte[] key, byte[] value);

	public String HGET(String key, String field);

	public Long HSET(String key, String field, String value);

	public Long HDEL(String key, String... fields);

	public Long SADD(String key, String... members);
	public List<String> LRANGE(String key, Long start,Long end);
	public Long LLEN(String key);

	public Long SCARD(String key);
	public Long LPUSH(String key,String... values);

	public Map<String, String> HGETALL(String key);

	public void select(int index);
}
