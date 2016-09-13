package com.alipp.weixin.service;

import java.util.List;
import java.util.Set;

import com.alipp.weixin.domain.RedisPair;
 
public interface RedisService {
	
	public void set(String key, Object value);
	
	public void setMulti(List<RedisPair> list);
	
	public Object get(String key);

	public void set(String key, Object value, int seconds);
	
	public <T> List<T> getMulti(String[] keys, Class<T> c);
	
	public void batchSet(List<RedisPair> list);

	public Set<String> keys(String keyPrefix);

	public void setList(String key, Object list);

	public Object getList(String key);

	public Long setInCr(String key);

	public void setExpire(String key, int seconds);

	public void del(String key);

}
