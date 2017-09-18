package org.kevin.nettylearn.messagepack;

import java.io.Serializable;

import org.msgpack.annotation.Message;

@Message
public class AddGoodsResp implements Serializable {
	private static final long serialVersionUID = -6785146976984183421L;
	private Long carId;

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	@Override
	public String toString() {
		return "AddGoodsResp [carId=" + carId + "]";
	}

}
