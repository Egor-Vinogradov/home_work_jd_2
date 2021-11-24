package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.model.Active;
import by.it_academy.jd2.food_control.model.Weighing;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ActiveAuditService {

    private final AuditService auditService;

    public ActiveAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ActiveJournalService.addItem(..))", returning = "result")
    public void addItemAudit(JoinPoint jp, Object result) {
        Active active = (Active) result;
        String text = "Создана новая активность " + active.getName();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.ACTIVE, active.getId(), ETypeAudit.CREATE));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ActiveJournalService.updateItem(..))", returning = "result")
    public void updateItemAudit(JoinPoint jp, Object result) {
        Active active = (Active) result;
        String text = "Обновлена активность " + active.getName();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.ACTIVE, active.getId(), ETypeAudit.EDIT));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ActiveJournalService.deleteItem(..))", returning = "result")
    public void deleteItemAudit(JoinPoint jp, Object result) {
        Object[] o = jp.getArgs();
        if ((boolean) result) {
            String text = "Удалена активность с id " + o[0];
            this.auditService.addItem(this.auditService.createAudit(text,
                    EEssenceName.ACTIVE, (Long) o[0], ETypeAudit.DELETE));
        }
    }
}
