package by.restonov.tyrent.tag;

import by.restonov.tyrent.manager.AttributeName;
import by.restonov.tyrent.manager.PageContentManager;
import by.restonov.tyrent.model.entity.impl.Car;
import by.restonov.tyrent.model.exception.ServiceException;
import by.restonov.tyrent.model.service.CarService;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * List of the Cars for the main page
 */
public class MainPageCarListTag extends TagSupport {
    private static final String CAR_COST_TEXT_KEY = "car.text.cost";
    private static final String BUTTON_SHOW_INFO_KEY = "main.button.show.info";

    @Override
    public int doStartTag() {
        String language = (String) pageContext.getSession().getAttribute(AttributeName.LOCALE);
        String carCostText = PageContentManager.findProperty(CAR_COST_TEXT_KEY, language);
        String buttonText = PageContentManager.findProperty(BUTTON_SHOW_INFO_KEY, language);
        CarService service = new CarService();
        List<Car> carList = null;
        try {
            carList = service.findCarList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        String cardFirstPart = """
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">
                        <img class="card-img-top" style="height: 225px; width: 100%; display: block;" src="
                """;
        String cardSecondPart = """
                        ">
                        <div class="card-body">
                            <p class="card-text">
                        """;
        String cardThirdPart = """                    
                            <br></p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <form method="GET" action="controller">
                                        <input class="btn btn-sm btn-outline-secondary" type="submit" value="
                            """;
        String cardFourthPart = """                
                        ">
                                <input type="hidden" name="chosen_car_id" value="
                        """;
        String cardFifthPart = """                                
                                        "/>
                                        <input type="hidden" name="command" value="show_car_info" />
                                    </form>
                                </div>
                                <small class="text-muted">
                                """;
        String cardSixthPart = """ 
                                </small>
                            </div>
                        </div>
                    </div>
                </div>
                """;
        StringBuilder card = new StringBuilder();
        assert carList != null;
        for (Car car : carList) {
                     card.append(cardFirstPart.strip())
                    .append(car.getImageMini().substring(1))
                    .append(cardSecondPart.strip())
                    .append(car.getName())
                    .append(cardThirdPart.strip())
                    .append(buttonText)
                    .append(cardFourthPart.strip())
                    .append(car.getId())
                    .append(cardFifthPart.strip())
                    .append(car.getCost().toString())
                    .append("\s")
                    .append(carCostText)
                    .append(cardSixthPart);
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(card.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}