package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.model.Dish;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class DishAuditService {

    private final AuditService auditService;

    public DishAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.DishService.addItem(..))", returning = "result")
    public void addItemAudit(JoinPoint jp, Object result) {
        String text = "Создано новое блюдо с id " + result;
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.DISH, (Long) result, ETypeAudit.CREATE));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.DishService.updateItem(..))", returning = "result")
    public void updateItemAudit(JoinPoint jp, Object result) {
        Dish dish = (Dish) result;
        String text = "Обновлено блюдо с id " + dish.getId();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.DISH, dish.getId(), ETypeAudit.EDIT));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.DishService.deleteId(..))", returning = "result")
    public void deleteItemAudit(JoinPoint jp, Object result) {
        Object[] o = jp.getArgs();
        if ((boolean) result) {
            String text = "Удалено блюдо с id " + o[0];
            this.auditService.addItem(this.auditService.createAudit(text,
                    EEssenceName.DISH, (Long) o[0], ETypeAudit.DELETE));
        }
    }
}
