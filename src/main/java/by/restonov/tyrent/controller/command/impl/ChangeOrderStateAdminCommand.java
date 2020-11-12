package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.entity.Car;
import by.restonov.tyrent.model.entity.UserOrder;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.CarService;
import by.restonov.tyrent.model.service.UserOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Change order state from admin panel
 *
 */
public class ChangeOrderStateAdminCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private UserOrderService orderService = new UserOrderService();
    private CarService carService = new CarService();

    /**
     * get "order_state" and "order_id" parameters from selected Order
     * check if orders exists in DB
     * get "car_id" from Order
     * change states of Car and Order according to selected parameter
     * and forward to user profile page
     *
     * @param request - HttpServletRequest
     * @return result page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String orderStateParam = request.getParameter(ParameterName.ORDER_STATE);
        UserOrder.State orderState = UserOrder.State.valueOf(orderStateParam);
        String orderId = request.getParameter(ParameterName.ORDER_ID);
        try {
            Optional<UserOrder> optionalOrder = orderService.findOrderById(Long.parseLong(orderId));
            if (optionalOrder.isPresent()) {
                UserOrder order = optionalOrder.get();
                orderService.changeOrderState(order, orderState);
                Optional<Car> optionalCar = carService.findCarById(order.getCarId());
                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    carService.alignCarStateToOrderState(car, orderState);
                } else {
                    logger.error("There is no order {} in DB", orderId);
                }
            } else {
                logger.error("No car attached to order {}", orderId);
            }
        } catch (ServiceException e) {
            logger.error("Change {} order state - error", orderId);
        }
        return PageName.ORDER_LIST_PAGE;
    }
}
