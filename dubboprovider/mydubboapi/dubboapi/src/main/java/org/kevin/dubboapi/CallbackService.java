package org.kevin.dubboapi;

public interface CallbackService {

	void addListener(String key, CallbackListener listener);
}
