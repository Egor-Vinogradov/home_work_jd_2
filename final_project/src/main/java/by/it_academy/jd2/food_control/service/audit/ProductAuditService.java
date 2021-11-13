package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.model.Product;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ProductAuditService {

    private final AuditService auditService;

    public ProductAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ProductService.addItem(..))", returning = "result")
    public void addItemAudit(JoinPoint jp, Object result) {
        String text = "Создан новый продукт с id " + result;
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.PRODUCT, (Long) result, ETypeAudit.CREATE));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ProductService.updateItem(..))", returning = "result")
    public void updateItemAudit(JoinPoint jp, Object result) {
        Product product = (Product) result;
        String text = "Обновлен продукт с id " + product.getId();
        this.auditService.addItem(this.auditService.createAudit(text,
                EEssenceName.PRODUCT, product.getId(), ETypeAudit.EDIT));
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.food_control.service.ProductService.deleteId(..))", returning = "result")
    public void deleteItemAudit(JoinPoint jp, Object result) {
        Object[] o = jp.getArgs();
        if ((boolean) result) {
            String text = "Удален продукт с id " + o[0];
            this.auditService.addItem(this.auditService.createAudit(text,
                    EEssenceName.PRODUCT, (Long) o[0], ETypeAudit.DELETE));
        }
    }
}
