package by.restonov.jspservlet.controller.command.impl;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.entity.Car;
import by.restonov.jspservlet.entity.User;
import by.restonov.jspservlet.entity.UserOrder;
import by.restonov.jspservlet.entity.builder.UserOrderBuilder;
import by.restonov.jspservlet.manager.AttributeName;
import by.restonov.jspservlet.manager.PageName;
import by.restonov.jspservlet.manager.ParameterName;
import by.restonov.jspservlet.model.service.CarService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class MakeOrderCommand implements ActionCommand {
    CarService service = new CarService();
    
    @Override
    public String execute(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter(ParameterName.CHOSEN_CAR_ID));
        String page = null;
        Optional<Car> optionalCar = service.findCarById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            if (car.getState() == Car.State.AVAILABLE) {
                User user = (User) request.getSession().getAttribute(AttributeName.USER);
                UserOrderBuilder builder = UserOrderBuilder.INSTANCE;
                UserOrder order = builder.build(car);
                user.setOrder(order);
                service.changeCarState(car, Car.State.BOOKED);
            } else {

            }
        } else {

            page = PageName.MAIN_PAGE;
        }
        return page;
    }
}
