package org.kevin.nettylearn.marshalling;

import java.io.Serializable;

public class AddShoppingCarResp implements Serializable {
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
		return "AddShoppingCarResp [carId=" + carId + "]";
	}

}
