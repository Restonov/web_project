package by.restonov.tyrent.controller.command.impl;

import by.restonov.tyrent.controller.command.ActionCommand;
import by.restonov.tyrent.model.entity.impl.Car;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageName;
import by.restonov.tyrent.manager.ParameterName;
import by.restonov.tyrent.model.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Show page with car info
 *
 */
public class ShowCarInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private CarService service = new CarService();

    /**
     * get "chosen_car_id" parameter from chosen "SHOW INFO" button
     * find available car in DB by "car_id"
     * if car available forward to page with car info
     * otherwise forward to main page with alert message
     *
     * @param request - HttpServletRequest
     * @return result page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String language = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        String carId = request.getParameter(ParameterName.CHOSEN_CAR_ID);
        Optional<Car> optionalCar = Optional.empty();
        try {
            optionalCar = service.findCarById(Long.parseLong(carId));
        } catch (ServiceException e) {
            logger.error("Error while searching Car by id");
        }
        if (optionalCar.isPresent()) {
            Car car = service.defineDescriptionAndGet(optionalCar.get(), language);
            request.setAttribute(AttributeName.CAR, car);
            page = PageName.CAR_INFO_PAGE;
        } else {
            request.setAttribute(AttributeName.CAR_NOT_AVAILABLE_ALERT, true);
            page = PageName.MAIN_PAGE;
        }
        return page;
    }
}
