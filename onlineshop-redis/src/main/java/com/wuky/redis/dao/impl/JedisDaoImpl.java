package com.wuky.redis.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.wuky.redis.dao.JedisDao;

import redis.clients.jedis.JedisCluster;


@Repository
public class JedisDaoImpl implements JedisDao
{
	@Resource
	private JedisCluster jedisClients;

	public Boolean exists(final String key)
	{
		return jedisClients.exists(key);
	}

	public Long del(final String key)
	{
		return jedisClients.del(key);
	}

	public String set(final String key, final String value)
	{
		return jedisClients.set(key, value);
	}

	public String get(final String key)
	{
		return jedisClients.get(key);
	}

	public Long expire(final String key, final int seconds)
	{
		return jedisClients.expire(key, seconds);
	}

}
