package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.model.Weighing;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class WeighingDiaryAuditService {

    private final AuditService auditService;

    public WeighingDiaryAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.WeighingJournalService.addItem(..))", returning = "result")
    public void addItemAudit(JoinPoint jp, Object result) {
        Weighing weighing = (Weighing) result;
        String text = "Создано новое взвешивание со значением " + weighing.getWeight() + " кг";
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.WEIGHTING, weighing.getId(), ETypeAudit.CREATE));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.WeighingJournalService.updateItem(..))", returning = "result")
    public void updateItemAudit(JoinPoint jp, Object result) {
        Weighing weighing = (Weighing) result;
        String text = "Обновлено взвешивание с id " + weighing.getId();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.WEIGHTING, weighing.getId(), ETypeAudit.EDIT));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.WeighingJournalService.deleteItem(..))", returning = "result")
    public void deleteItemAudit(JoinPoint jp, Object result) {
        Object[] o = jp.getArgs();
        if ((boolean) result) {
            String text = "Удалено взвешивание с id " + o[0];
            this.auditService.addItem(this.auditService.createAudit(text,
                    EEssenceName.WEIGHTING, (Long) o[0], ETypeAudit.DELETE));
        }
    }
}
