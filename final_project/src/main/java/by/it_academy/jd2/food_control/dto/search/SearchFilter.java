package by.it_academy.jd2.food_control.dto.search;

public class SearchFilter {
    private Long page;
    private Long size;
    private String name;
    private String login;

    private Long dataStart;
    private Long dataEnd;

    private Long day;

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getDataStart() {
        return dataStart;
    }

    public void setDataStart(Long dataStart) {
        this.dataStart = dataStart;
    }

    public Long getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(Long dataEnd) {
        this.dataEnd = dataEnd;
    }
}
