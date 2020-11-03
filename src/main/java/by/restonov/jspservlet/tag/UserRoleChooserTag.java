package by.restonov.jspservlet.tag;

import by.restonov.jspservlet.entity.User;

public class UserRoleChooserTag {
    private String showUserRoleBlock(User user) {
        StringBuilder userRoleBlock = new StringBuilder();
        userRoleBlock.append("<form action=\"controller\" method=\"post\">")
                .append("<p style=\"float: left\">")
                .append("<select name=\"user_role\">")
                .append("<option hidden>").append(user.getRole().toString().toLowerCase()).append("</option>")
                .append("<option value=administrator>").append(user.getRole().toString().toLowerCase()).append("</option>")
                .append("</select>")
                .append("<input type=\"hidden\" name=\"command\" value=\"change_role\" />")
                .append("<input type=\"submit\" value=\"OK\">")
                .append(" </form>");


//
//
//                        "                <option value=\"Чебурашка\">Чебурашка</option>\n" +
//                        "                <option value=\"Шапокляк\">Шапокляк</option>\n" +
//                        "                <option value=\"Крыса Лариса\">Крыса Лариса</option>\n" +
//                        "            </select>\n" +
//                        "            </p>\n" +
//                        "            <p style=\"float: right; margin-left: 5px\">
//                        <input type=\"submit\" value=\"OK\"></p>\n" +
//                        "            <input type=\"hidden\" name=\"command\" value=\"change_role\" />\n" +
//                        "            </form>\n" +
//                        "        </div>";

        return userRoleBlock.toString();
    }
}
