package com.alipp.weixin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.alipp.weixin.domain.RedisPair;
import com.alipp.weixin.service.RedisService;
import com.alipp.weixin.util.ListTranscoder;
import com.alipp.weixin.util.SerializingUtil;
import com.alipp.weixin.util.StringUtil;

@Service
public class RedisServiceImpl implements RedisService{

	@Autowired
	private JedisPool jedisPool;

	/**
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value){
		if(StringUtil.isBlank(key) || value==null){
			return;
		}
		Jedis jedis = jedisPool.getResource();
		jedis.mset(key.getBytes(), SerializingUtil.serialize(value));
		jedisPool.returnResource(jedis);
	}
	
	public void setMulti(List<RedisPair> list){
		if(list==null || list.size()==0){
			return;
		}
		Jedis jedis = jedisPool.getResource();
		byte[][] values = new byte[2*list.size()][];
		int j = 0;
		for(int i=0;i<list.size();i++){
			RedisPair pair = list.get(i);
			values[j] = pair.getKey().getBytes();
			values[j+1] = SerializingUtil.serialize(pair.getValue());
			j = j+2;
		}
		jedis.mset(values);
		jedisPool.returnResource(jedis);
	}
	
	/**
	 * @param key
	 * @param value
	 * @param seconds
	 */
	@Override
	public void set(String key, Object value, int seconds){
		if(StringUtil.isBlank(key) || value==null){
			return;
		}
		Jedis jedis = jedisPool.getResource();
		jedis.setex(key.getBytes(), seconds, SerializingUtil.serialize(value));
		jedisPool.returnResource(jedis);
	}
	
	/**
	 * @param key
	 * @return
	 */
	public Object get(String key){
		if(StringUtil.isBlank(key)){
			return null;
		}
		Jedis jedis = jedisPool.getResource();
		Object valueObj = jedis.get(key.getBytes());
		jedisPool.returnResource(jedis);
		if(valueObj!=null){
			return SerializingUtil.deserialize((byte[])valueObj);
		}else{
			return null;
		}
		
	}
	
	/**
	 * @param <T>
	 * @param key
	 * @return
	 */
	public <T> List<T> getMulti(String[] keys, Class<T> c){
		if(keys==null || keys.length==0){
			return null;
		}
		Jedis jedis = jedisPool.getResource();
		byte[][] keyBytes = new byte[keys.length][];
		for(int i=0; i<keys.length;i++){
			keyBytes[i] = keys[i].getBytes();
		}
		List<byte[]> valueObj = jedis.mget(keyBytes);
		jedisPool.returnResource(jedis);
		List<T> list = new ArrayList<T>();
		if(valueObj!=null && valueObj.size()>0){
			for(byte[] bytes : valueObj){
				if (bytes != null) {
					@SuppressWarnings("unchecked")
					T t = (T)(SerializingUtil.deserialize(bytes));
					list.add(t);
				}
			}
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * 用Pipeline批量存到redis
	 * @param List<RedisPair>
	 */
	@Override
	public void batchSet(List<RedisPair> list) {
		Pipeline pipeline = jedisPool.getResource().pipelined();
		for (RedisPair redisPair : list) {
			pipeline.set(redisPair.getKey().getBytes(), 
					SerializingUtil.serialize(redisPair.getValue()));
		}
		pipeline.sync();
	}
	
	@Override
	public Set<String> keys(String keyPrefix) {
		Pipeline pipeline = jedisPool.getResource().pipelined();
		Response<Set<String>> response = pipeline.keys(keyPrefix);
		pipeline.sync();
		return response.get();
	}
	
	@Override
	public void setList(String key, Object list) {
		Jedis jedis = jedisPool.getResource();
		jedis.set(key.getBytes(), ListTranscoder.serialize(list));
		jedisPool.returnResource(jedis);
	}
	
	@Override
	public Object getList(String key) {
		Jedis jedis = jedisPool.getResource();
		byte[] result = jedis.get(key.getBytes());
		return ListTranscoder.deserialize(result); 
	}

	/**
	 * redis本身的原子自增操作
	 * @param key key
	 */
	@Override
	public Long setInCr(String key){
		if(StringUtil.isBlank(key)){
			return 0L;
		}
		Jedis jedis = jedisPool.getResource();
		Long inCr = jedis.incr(key.getBytes());
		jedisPool.returnResource(jedis);
		return inCr;
	}

	/**
	 * 设置key的有效时间
	 * @param key key
	 * @param seconds 时间
	 */
	@Override
	public void setExpire(String key,int seconds){
		if(StringUtil.isBlank(key)){
			return;
		}
		Jedis jedis = jedisPool.getResource();
		jedis.expire(key.getBytes(),seconds);
		jedisPool.returnResource(jedis);
	}

	@Override
	public void del(String key){
		if(StringUtil.isBlank(key)){
			return;
		}
		Jedis jedis = jedisPool.getResource();
		jedis.del(key.getBytes());
		jedisPool.returnResource(jedis);
	}

}
