package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.model.Dish;
import by.it_academy.jd2.food_control.model.Recipe;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class RecipeAuditService {

    private final AuditService auditService;

    public RecipeAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.RecipeService.addItem(..))", returning = "result")
    public void addItemAudit(JoinPoint jp, Object result) {
        String text = "Создан новый рецепт с id " + result;
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.RECIPE, (Long) result, ETypeAudit.CREATE));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.RecipeService.updateItem(..))", returning = "result")
    public void updateItemAudit(JoinPoint jp, Object result) {
        Recipe recipe = (Recipe) result;
        String text = "Обновлен рецепт с id " + recipe.getId();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.RECIPE, recipe.getId(), ETypeAudit.EDIT));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.RecipeService.deleteId(..))", returning = "result")
    public void deleteItemAudit(JoinPoint jp, Object result) {
        Object[] o = jp.getArgs();
        if ((boolean) result) {
            String text = "Удален рецепт с id " + o[0];
            this.auditService.addItem(this.auditService.createAudit(text,
                    EEssenceName.RECIPE, (Long) o[0], ETypeAudit.DELETE));
        }
    }
}
