package by.it_academy.jd2.food_control.service.audit;

import by.it_academy.jd2.food_control.dao.api.IAuditDao;
import by.it_academy.jd2.food_control.model.Audit;
import by.it_academy.jd2.food_control.model.User;
import by.it_academy.jd2.food_control.model.api.EEssenceName;
import by.it_academy.jd2.food_control.model.api.ETypeAudit;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.service.UserService;
import by.it_academy.jd2.food_control.service.api.IService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService implements IService<Audit, Long> {

    private final IAuditDao repository;
    private Authentication auth;
    private final UserService userService;

    public AuditService(IAuditDao repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
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
        int offset = Math.toIntExact((filter.getPage() != null) ? filter.getPage() : 0);
        int limit = Math.toIntExact((filter.getSize() != null) ? filter.getSize() : Integer.MAX_VALUE);

        User user = null;
        if (filter.getLogin() != null) {
            user = this.userService.findByLogin(filter.getLogin());
        }

        try {
            if (user != null) {
                return this.repository.findByUser(user, PageRequest.of(offset, limit));
            } else {
                return this.repository.findAllBy(PageRequest.of(offset, limit));
            }

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteId(Long id, Long version) {
        return false;
    }

    @Override
    public Audit updateItem(Long id, Audit item, Long version) {
        return null;
    }

    public Audit createAudit(String description, EEssenceName essenceName, Long essenceId,
                             ETypeAudit typeAudit) {
        Audit audit = new Audit();
        audit.setDescription(description);
        audit.setEssenceName(essenceName);
        audit.setEssenceId(essenceId);
        audit.setTypeAudit(typeAudit);

        this.auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.findByLogin(auth.getName());

        audit.setUser(user);

        return audit;
    }
}
