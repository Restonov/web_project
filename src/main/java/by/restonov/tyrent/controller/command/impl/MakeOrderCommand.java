package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.entity.Car;
import by.restonov.tyrent.entity.User;
import by.restonov.tyrent.entity.UserOrder;
import by.restonov.tyrent.exception.ServiceException;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.service.CarService;
import by.restonov.tyrent.model.service.UserOrderService;
import by.restonov.tyrent.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class MakeOrderCommand implements ActionCommand {
    private UserService userService = new UserService();
    private CarService carService = new CarService();
    private UserOrderService orderService = new UserOrderService();
    
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        int carId = Integer.parseInt(request.getParameter(ParameterName.CHOSEN_CAR_ID));
        Optional<Car> optionalCar = carService.checkCarAvailability(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            User user = (User) session.getAttribute(AttributeName.USER);
            try {
                UserOrder order = orderService.createOrder(carId);
                user = userService.addOrderIdToUserAndGet(user, order.getOrderId());
                carService.changeCarState(car, Car.State.BOOKED);
                session.setAttribute(AttributeName.USER, user);
                request.setAttribute(AttributeName.SUCCESSFUL_ORDER, true);
                page = PageName.USER_PROFILE_PAGE;
            } catch (ServiceException e) {
                page = PageName.ERROR_500_PAGE;
                e.printStackTrace();
            }
        } else {
            request.setAttribute(AttributeName.CAR_NOT_AVAILABLE_ALERT, true);
            page = PageName.MAIN_PAGE;
        }
        return page;
    }
}
