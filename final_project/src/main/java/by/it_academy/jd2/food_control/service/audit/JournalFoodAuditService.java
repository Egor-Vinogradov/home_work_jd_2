package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.model.JournalFood;
import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class JournalFoodAuditService {

    private final AuditService auditService;

    public JournalFoodAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.JournalFoodService.addItem(..))", returning = "result")
    public void addItemAudit(JoinPoint jp, Object result) {
        JournalFood journalFood = (JournalFood) result;
        String text = "Создана запись в журнеле питания с id " + journalFood.getId();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.JOURNAL, journalFood.getId(), ETypeAudit.CREATE));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.JournalFoodService.updateItem(..))", returning = "result")
    public void updateItemAudit(JoinPoint jp, Object result) {
        JournalFood journal = (JournalFood) result;
        String text = "Обновлена запись в журнеле питания с id " + journal.getId();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.JOURNAL, journal.getId(), ETypeAudit.EDIT));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.JournalFoodService.deleteItem(..))", returning = "result")
    public void deleteItemAudit(JoinPoint jp, Object result) {
        Object[] o = jp.getArgs();
        if ((boolean) result) {
            String text = "Удалена запись из журнела питания продукт с id " + o[0];
            this.auditService.addItem(this.auditService.createAudit(text,
                    EEssenceName.JOURNAL, (Long) o[1], ETypeAudit.DELETE));
        }
    }
}
