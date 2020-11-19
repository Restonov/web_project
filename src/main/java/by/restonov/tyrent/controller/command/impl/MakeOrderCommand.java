package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.model.entity.impl.Car;
import by.restonov.tyrent.model.entity.impl.User;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.service.CarService;
import by.restonov.tyrent.model.service.UserOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Create new order command
 *
 */
public class MakeOrderCommand implements ActionCommand {
    private CarService carService = new CarService();
    private UserOrderService orderService = new UserOrderService();

    /**
     * get "chosen_car_id" parameter from chosen "BOOK CAR" button
     * find available car in DB by "car_id"
     * if car available create new order, attach car and user
     * and forward to user profile page
     * otherwise forward to main page with alert message
     *
     * @param request - HttpServletRequest
     * @return result page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        long carId = Long.parseLong(request.getParameter(ParameterName.CHOSEN_CAR_ID));
        Optional<Car> optionalCar;
        try {
            optionalCar = carService.checkCarAvailability(carId);
            if (optionalCar.isPresent()) {
                Car car = optionalCar.get();
                User user = (User) session.getAttribute(AttributeName.USER);
                orderService.addOrder(user.getId(), carId);
                carService.changeCarState(car, Car.State.BOOKED);
                request.setAttribute(AttributeName.SUCCESSFUL_ORDER_MESSAGE, true);
                page = PageName.USER_PROFILE_PAGE;
            } else {
                request.setAttribute(AttributeName.CAR_NOT_AVAILABLE_ALERT, true);
                page = PageName.MAIN_PAGE;
            }
        } catch (ServiceException e) {
            request.setAttribute(AttributeName.CAR_NOT_AVAILABLE_ALERT, true);
            page = PageName.MAIN_PAGE;
        }
        return page;
    }
}
