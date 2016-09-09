package com.longrise.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.longrise.common.utils.Global;

import redis.clients.jedis.Jedis;

/**
 * Redis操作接口
 * 
 */
public class RedisAPI implements RedisInterface
{
    private int index = RedisAPI.TABLE_DEFAULT;

    private Jedis getResource ()
    {
        return MyJedisPool.getInstance().getResource();
    }

    public RedisAPI()
    {
        if ( Global.getRedisDefaultTable() != null )
        {
            index = Global.getRedisDefaultTable();
        }
    }

    public RedisAPI( int index )
    {
        this.index = index;
    }

    /**
     * 从默认表中获取数据
     * 
     * @param key
     * @return
     */
    public String get ( String key )
    {
        String value = null;
        Jedis jedis = null;
        try
        {
            jedis = MyJedisPool.getInstance().getResource();
            jedis.select(index);
            value = jedis.get(key);
        }
        catch (Exception e)
        {
            // 释放redis对象
            e.printStackTrace();
        }
        finally
        {
            // 返还到连接池
            if ( jedis != null )
                jedis.close();
        }
        return value;
    }

    /**
     * 从指定的表中获取数据
     * 
     * @param key
     * @param index
     * @return
     */
    public String get ( String key , int index )
    {
        String value = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            value = jedis.get(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }

        return value;
    }

    public Set<String> keys ()
    {
        Set<String> value = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            value = jedis.keys("*");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }

        return value;
    }

    public Set<String> keys ( int index )
    {
        Set<String> value = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            value = jedis.keys("*");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return value;
    }

    public String set ( String key , String value )
    {
        String ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.set(key, value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }

        return ret;
    }

    public String set ( String key , String value , int index )
    {
        String ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.set(key, value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;

    }

    public String set ( String key , int expire , String value )
    {
        String ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.set(key, value);
            jedis.expire(key, expire);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public String set ( String key , int expire , String value , int index )
    {
        String ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.set(key, value);
            jedis.expire(key, expire);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public Long delete ( String key )
    {
        Long ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.del(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public Long delete ( String key , int index )
    {
        Long ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.del(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public Long append ( String key , String value )
    {
        Long ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.append(key, value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public Long append ( byte[] key , byte[] value )
    {
        Long ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.append(key, value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public String HGET ( String key , String field )
    {
        String value = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            value = jedis.hget(key, field);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }

        return value;

    }

    public Long HSET ( String key , String field , String value )
    {
        Long ret = 0L;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.hset(key, field, value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public Long HDEL ( String key , String... fields )
    {
        Long ret = 0L;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.hdel(key, fields);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;

    }

    public Map<String, String> HGETALL ( String key )
    {
        Map<String, String> ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.hgetAll(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public void select ( int index )
    {
        this.index = index;
    }

    public Long SADD ( String key , String... members )
    {
        Long ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.sadd(key, members);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public Long SCARD ( String key )
    {
        Long ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.scard(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public Long LPUSH ( String key , String... values )
    {
        Long ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.lpush(key, values);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public List<String> LRANGE ( String key , Long start , Long end )
    {
        List<String> ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.lrange(key, start, end);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }

    public Long LLEN ( String key )
    {
        Long ret = null;
        Jedis jedis = null;
        try
        {
            jedis = getResource();
            jedis.select(index);
            ret = jedis.llen(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( jedis != null )
                jedis.close();
        }
        return ret;
    }
}
