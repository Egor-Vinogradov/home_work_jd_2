package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.model.Profile;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ProfileAuditService {

    private final AuditService auditService;

    public ProfileAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ProfileService.addItem(..))", returning = "result")
    public void addItemAudit(JoinPoint jp, Object result) {
        String text = "Создан новый профиль с id " + result;
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.PROFILE, (Long) result, ETypeAudit.CREATE));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ProfileService.updateItem(..))", returning = "result")
    public void updateItemAudit(JoinPoint jp, Object result) {
        Profile profile = (Profile) result;
        String text = "Обновлен профиль с id " + profile.getId();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.PROFILE, profile.getId(), ETypeAudit.EDIT));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ProfileService.deleteId(..))", returning = "result")
    public void deleteItemAudit(JoinPoint jp, Object result) {
        Object[] o = jp.getArgs();
        if ((boolean) result) {
            String text = "Удален профиль с id " + o[0];
            this.auditService.addItem(this.auditService.createAudit(text,
                    EEssenceName.PROFILE, (Long) o[0], ETypeAudit.DELETE));
        }
    }
}
