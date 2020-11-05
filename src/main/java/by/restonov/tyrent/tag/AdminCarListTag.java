package by.restonov.tyrent.tag;

import by.restonov.tyrent.entity.Car;
import by.restonov.tyrent.model.service.CarService;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class AdminCarListTag extends TagSupport {

    @Override
    public int doStartTag() {
        CarService service = new CarService();
        List<Car> carList = service.findCarList();

        StringBuilder table = new StringBuilder();
        for (Car car : carList) {
           table.append("<tr>")
                   .append("<th scope=\"row\">").append(car.getId()).append("</th>")
                   .append("<td>").append(car.getName()).append("</td>")
                   .append("<td>").append(car.getCost()).append("</td>")
                   .append("<td>").append(car.getType()).append("</td>")
                   .append("<td>").append(car.getDescriptionEng()).append("</td>")
                   .append("<td>").append(car.getDescriptionRus()).append("</td>")
                   .append("<td>")
                   .append(car.getImageBackground()).append("\n")
                   .append(car.getImageMini()).append("\n")
                   .append(car.getImageCabin())
                   .append("</td>")
                   .append("</tr>");
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(table.toString());
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
