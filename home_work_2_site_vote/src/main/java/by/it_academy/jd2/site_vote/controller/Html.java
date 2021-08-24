package by.it_academy.jd2.site_vote.controller;

import by.it_academy.jd2.site_vote.service.Utils;
import by.it_academy.jd2.site_vote.service.VoteService;

import java.util.List;
import java.util.Map;

public class Html {
    private static Html instance;
    private final VoteService service;

    private Html() {
        this.service = VoteService.getInstance();
    }

    public static Html getInstance() {
        if(instance == null) {
            instance = new Html();
        }
        return instance;
    }

    public String initPages(String alert) {
        if (alert == null) {
            alert = "";
        }
        return "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "    <title>Голосование</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "  \t<form action=\"/Site_vote-1.0/\" method=\"POST\">\n" +
                "      <h1>Голосование</h>\n" +
                "        \n" +
                alert +
                "  \t\t<h3>Артист:</h3> \t\t\n" +
                "      <select class=\"form-select\" name=\"artist\" aria-label=\"Default select example\">\n" +
                "        <option value=\"1\">Ирина Олегрова</option>\n" +
                "        <option value=\"2\">Каста</option>\n" +
                "        <option value=\"3\">Луна</option>\n" +
                "        <option value=\"4\">Иванушки</option>\n" +
                "      </select>\n" +
                "      \n" +
                "  \t\t<h3>Жанр</h3>\n" +
                "  \t\t<input type=\"checkbox\" name=\"genre\" value=\"1\"> Рок <br>\n" +
                "  \t\t<input type=\"checkbox\" name=\"genre\" value=\"2\"> Поп <br>\n" +
                "  \t\t<input type=\"checkbox\" name=\"genre\" value=\"3\"> Фолк <br>\n" +
                "  \t\t<input type=\"checkbox\" name=\"genre\" value=\"4\"> Альт <br>\n" +
                "  \t\t<input type=\"checkbox\" name=\"genre\" value=\"5\"> Клкассика <br>\n" +
                "  \t\t<input type=\"checkbox\" name=\"genre\" value=\"6\"> Джаз <br>\n" +
                "  \t\t<input type=\"checkbox\" name=\"genre\" value=\"7\"> Тиктоник <br>\n" +
                "      \n" +
                "  \t\t<h3>О себе:</h3>\n" +
                "      <div class=\"mb-3\">\n" +
                "        <textarea class=\"form-control\" name=\"about\" id=\"exampleFormControlTextarea1\" rows=\"3\"></textarea>\n" +
                "      </div>\n" +
                "      <div class=\"btn-group-vertical\">\n" +
                "        <button type=\"submit\" class=\"btn btn-primary\" name=\"Отправить\">Отправить</button>\n" +
                "        <button type=\"submit\" class=\"btn btn-success\" name=\"result\" volume=\"result\">Результат</button>\n" +
                "      </div>" +
                "  \t</form>\n" +
                "  </body>\n" +
                "</html>";
    }

    public String alertMessage() {
        return "<div class=\"alert alert-primary d-flex align-items-center\" role=\"alert\">\n" +
                "  <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" fill=\"currentColor\" class=\"bi bi-exclamation-triangle-fill flex-shrink-0 me-2\" viewBox=\"0 0 16 16\" role=\"img\" aria-label=\"Warning:\">\n" +
                "    <path d=\"M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\"/>\n" +
                "  </svg>\n" +
                "  <div>\n" +
                "    Необходимо выбрать не менее 3-х жанров\n" +
                "  </div>\n" +
                "</div>";
    }

    public String initPageResult(Map<String, Integer> artistResult, Map<String, Integer> genreResult,
                                 List<String> aboutResult) {
        return "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "    <title>Результаты голосования</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "  \t<form action=\"/Site_vote-1.0/\" method=\"GET\">\n" +
                "      <h1>Результаты голосования</h>\n" +
                "\n" +
                "        <h3>Артист:</h3>\n" +
                pageResultArtistGenre(artistResult, "Artist") +
                "        <h3>Жанр:</h3>\n" +
                pageResultArtistGenre(genreResult, "Genre") +
                "        <h3>О себе:</h3>\n" +
                pageResultAbout(aboutResult) +
                "        <button type=\"submit\" class=\"btn btn-primary\">Назад</button>\n" +
                "        <button type=\"submit\" class=\"btn btn-success\" name=\"save\" volume=\"save\">Сохранить результат</button>\n" +
                "  \t</form>\n" +
                "  </body>\n" +
                "</html>";
    }

    private String pageResultArtistGenre(Map<String, Integer> map, String value) {
        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"container\">\n" +
                "        <div class=\"row justify-content-start\">");

        Map<String, Integer> newMap = Utils.sortedMap(map);

        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
            builder.append("<div class=\"col-4\">");
            switch (value) {
                case "Artist":
                    builder.append(this.service.getArtistName(entry.getKey()));
                    break;
                case "Genre":
                    builder.append(this.service.getGenreName(entry.getKey()));
                    break;
                default:
                    builder.append("");
            }
            builder.append("</div>");
            builder.append("<div class=\"col-4\">");
            builder.append(entry.getValue());
            builder.append("</div>");
            builder.append("<div class=\"col-4\">");
            builder.append("</div>");
        }

        builder.append("</div>\n" +
                "      </div>");


        return builder.toString();
    }

    private String pageResultAbout(List<String> aboutResult) {
        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"container\">\n" +
                "        <div class=\"row justify-content-start\">");

        for (String string : aboutResult) {
            if (string != null) {
                builder.append("<div class=\"col-4\">");
                builder.append(string);
                builder.append("</div>");
                builder.append("<div class=\"col-4\">");
                builder.append("</div>");
                builder.append("<div class=\"col-4\">");
                builder.append("</div>");
            }
        }

        builder.append("</div>\n" +
                "      </div>");

        return builder.toString();
    }
}
