package by.it_academy.jd2.crm.model.filter;

public class EmployeeSearchFilter extends PageableFilter {
    private EPredicateOperator predicateOperator;
    private String name;
    private ESalaryOperator salaryOperator;
    private Double from;
    private Double to;
    private Double salary;

    public EmployeeSearchFilter(int offset, int limit,
                                ESortDirection sortDirection,
                                EPredicateOperator predicateOperator,
                                String name, ESalaryOperator salaryOperator,
                                Double from, Double to, Double salary) {
        super(offset, limit, sortDirection);
        this.predicateOperator = predicateOperator;
        this.name = name;
        this.salaryOperator = salaryOperator;
        this.from = from;
        this.to = to;
        this.salary = salary;
    }

    public EPredicateOperator getPredicateOperator() {
        return predicateOperator;
    }

    public String getName() {
        return name;
    }

    public ESalaryOperator getSalaryOperator() {
        return salaryOperator;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getSalary() {
        return salary;
    }
}
