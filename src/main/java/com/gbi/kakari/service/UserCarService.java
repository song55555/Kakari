package com.gbi.kakari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gbi.kakari.dto.OrderDTO;
import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.car.UserCar;
import com.gbi.kakari.entity.car.option.UserCarOption;
import com.gbi.kakari.entity.order.CarOrder;
import com.gbi.kakari.repository.car.UserCarRepository;
import com.gbi.kakari.repository.car.option.UserCarOptionRepository;
import com.gbi.kakari.repository.order.CarOrderRepository;

@Service
public class UserCarService {
	private final UserCarRepository userCarRepository;
	private final UserCarOptionRepository userCarOptionRepository;
	private final CarOrderRepository carOrderRepository;

	@Autowired
	public UserCarService(
		UserCarRepository userCarRepository, UserCarOptionRepository userCarOptionRepository,
		CarOrderRepository carOrderRepository) {
		this.userCarRepository = userCarRepository;
		this.userCarOptionRepository = userCarOptionRepository;
		this.carOrderRepository = carOrderRepository;
	}

	@Transactional
	public Integer orderCar(String userId, Product product, OrderDTO orderDTO) {
		UserCar userCar = registerUserCar(userId, product.getProductId(), orderDTO.getProductColorId());
		registerUserCarOptions(userCar.getUserCarId(), orderDTO.getSelectedOptions());
		
		Integer userCarId = userCar.getUserCarId();
		CarOrder order = CarOrder.builder()
			.userId(userId)
			.userCarId(userCarId)
			.orderPrice(product.getPrice())
			.orderAddress(orderDTO.getOrderAddress()).build();
		return carOrderRepository.save(order).getOrderId();
	}

	private UserCar registerUserCar(String userId, Integer productId, Integer productColorId) {
		UserCar userCar = new UserCar();
		userCar.setUserId(userId);
		userCar.setProductId(productId);
		userCar.setProductColorId(productColorId);
		return userCarRepository.save(userCar);
	}

	private void registerUserCarOptions(Integer userCarId, List<Integer> selectedOptions) {
		if (selectedOptions != null && !selectedOptions.isEmpty()) {
			for (Integer productOptionId : selectedOptions) {
				UserCarOption userCarOption = new UserCarOption();
				userCarOption.setUserCarId(userCarId);
				userCarOption.setProductOptionId(productOptionId);

				userCarOptionRepository.save(userCarOption);
			}
		}
	}

	public List<UserCar> getUserCars(String userId) {
		return userCarRepository.findByUserId(userId);
	}

	public List<UserCarOption> getUserCarOptions(Integer userCarId) {
		return userCarOptionRepository.findByUserCarId(userCarId);
	}
}
