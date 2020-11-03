package by.restonov.jspservlet.controller.command.impl;

import by.restonov.jspservlet.controller.command.ActionCommand;
import by.restonov.jspservlet.entity.Car;
import by.restonov.jspservlet.manager.AttributeName;
import by.restonov.jspservlet.manager.PageName;
import by.restonov.jspservlet.manager.ParameterName;
import by.restonov.jspservlet.model.service.CarService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowCarInfoCommand implements ActionCommand {
    private CarService service = new CarService();

    @Override
    public String execute(HttpServletRequest request) {
        String language = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        String page;
        String carId = request.getParameter(ParameterName.CHOSEN_CAR_ID);
        Optional<Car> optionalCar = service.findCarById(Integer.parseInt(carId));
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            String carDescription = language.equals(AttributeName.EN) ? car.getDescriptionEng() : car.getDescriptionRus();
            car.setDescription(carDescription);
            request.setAttribute(AttributeName.CAR, car);
        }
        page = PageName.CAR_INFO_PAGE;
        return page;
    }
}
