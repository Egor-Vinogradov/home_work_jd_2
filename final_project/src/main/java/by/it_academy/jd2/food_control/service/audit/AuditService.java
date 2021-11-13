package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.dao.api.IAuditDao;
import by.it_academy.jd2.food_control.model.Audit;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import by.it_academy.jd2.food_control.model.search.SearchFilter;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService implements IService<Audit, Long> {

    private final IAuditDao repository;

    public AuditService(IAuditDao repository) {
        this.repository = repository;
    }

    @Override
    public Long addItem(Audit item) {
        item.setCreationDate(LocalDateTime.now());
        return this.repository.save(item).getId();
    }

    @Override
    public Audit findById(Long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Аудит по id не найден")
        );
    }

    @Override
    public List<Audit> findAll(SearchFilter filter) {
        return null;
    }

    @Override
    public boolean deleteId(Long id, Long version) {
        return false;
    }

    @Override
    public Audit updateItem(Long id, Audit item) {
        return null;
    }

    public Audit createAudit(String description, EEssenceName essenceName, Long essenceId,
                            ETypeAudit typeAudit) {
        Audit audit = new Audit();
        audit.setDescription(description);
        audit.setEssenceName(essenceName);
        audit.setEssenceId(essenceId);
        audit.setTypeAudit(typeAudit);

        return audit;
    }
}
