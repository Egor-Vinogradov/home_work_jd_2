package by.it_academy.jd2.crm.model.filter;

public class PageableFilter {
    private int offset;
    private int limit;
    private ESortDirection sortDirection;

    public PageableFilter(int offset, int limit, ESortDirection sortDirection) {
        this.offset = offset;
        this.limit = limit;
        this.sortDirection = sortDirection;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public ESortDirection getSortDirection() {
        return sortDirection;
    }
}
