package by.restonov.jspservlet.controller.command.impl;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.entity.Car;
import by.restonov.jspservlet.entity.User;
import by.restonov.jspservlet.entity.UserOrder;
import by.restonov.jspservlet.exception.ServiceException;
import by.restonov.jspservlet.manager.AttributeName;
import by.restonov.jspservlet.manager.PageName;
import by.restonov.jspservlet.manager.ParameterName;
import by.restonov.jspservlet.model.service.CarService;
import by.restonov.jspservlet.model.service.UserOrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class MakeOrderCommand implements ActionCommand {
    private CarService carService = new CarService();
    private UserOrderService orderService = new UserOrderService();
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int carId = Integer.parseInt(request.getParameter(ParameterName.CHOSEN_CAR_ID));
        Optional<Car> optionalCar = carService.checkCarAvailability(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            User user = (User) request.getSession().getAttribute(AttributeName.USER);
            UserOrder order = null;
            try {
                order = orderService.makeOrder(carId);
                user.setOrder(order);
                carService.changeCarState(car, Car.State.BOOKED);
                request.setAttribute(AttributeName.CAR, car);
                page = PageName.CAR_INFO_PAGE;
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            page = PageName.MAIN_PAGE;
        }
        return page;
    }
}
