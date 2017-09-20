package org.kevin.dubboapi2;

public interface CallbackService {

	void addListener(String key, CallbackListener listener);
}
