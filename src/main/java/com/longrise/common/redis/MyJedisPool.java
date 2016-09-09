package com.longrise.common.redis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.longrise.common.utils.Global;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class MyJedisPool extends JedisPool {
	final static Logger Log = LoggerFactory.getLogger(MyJedisPool.class);
	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	private static MyJedisPool pool = null;
	private static int MaxTotal = 100;
	private static int MaxIdle = 5;
	private static String PASSWORD = "longrise";
	private static int PORT = 6379;
	private static String HOST = "59.173.241.186";

	private MyJedisPool() {
	}

	private MyJedisPool(JedisPoolConfig config, String ip, int port) {
		super(config, ip, port);
	}

	private MyJedisPool(final JedisPoolConfig config, final String host, int port, int timeout, final String password,
			final int database) {
		super(config, host, port, timeout, password, database);
	}

	public static MyJedisPool getInstance() {
		setConfig();
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxTotal(MaxTotal);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(MaxIdle);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWaitMillis(1000 * 60);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			if (PASSWORD != null)
				pool = new MyJedisPool(config, HOST, PORT, Protocol.DEFAULT_TIMEOUT, PASSWORD,
						Protocol.DEFAULT_DATABASE);
			else
				pool = new MyJedisPool(config, HOST, PORT);
		}
		return pool;
	}

	private static void setConfig() {
		InputStream in = null;
		try {
			in = new FileInputStream(Global.getWebRootRealPath() + "WEB-INF/redis.properties");
		} catch (FileNotFoundException e) {
			Log.error("未找到redis配置文件，请检查redis配置文件！",e);
		}
		Properties prop = new Properties();
		try {
			prop.load(in);
			HOST = prop.getProperty("host");
			PORT = Integer.valueOf(prop.getProperty("port"));
			PASSWORD = prop.getProperty("password");
			MaxIdle = Integer.valueOf(prop.getProperty("maxidle"));
			MaxTotal = Integer.valueOf(prop.getProperty("maxtotal"));
			Global.setRedisDefaultTable(Integer.valueOf(prop.getProperty("tabledefault")));
		} catch (Exception e) {
			Log.error("redis配置文件解析失败，请检查redis配置文件！",e);
		}
	}

	/**
	 * 回收到连接池
	 * 
	 * @param redis
	 */
	public void returnResource(Jedis redis) {
		if (redis != null) {
			super.returnResource(redis);
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param redis
	 */
	public void returnBrokenResource(Jedis redis) {
		if (redis != null) {
			super.returnBrokenResource(redis);
		}
	}
}
