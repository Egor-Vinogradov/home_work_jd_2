package by.it_academy.jd2.food_control.service;

import by.it_academy.jd2.food_control.dao.api.IWeighingDiaryDao;
import by.it_academy.jd2.food_control.model.Weighing;
import by.it_academy.jd2.food_control.service.api.ISearchProfileCreatedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeighingSearchService implements ISearchProfileCreatedDate<Weighing> {

    private final IWeighingDiaryDao repository;

    public WeighingSearchService(IWeighingDiaryDao repository) {
        this.repository = repository;
    }

    @Override
    public Weighing findFirstCreationDate(Long idProfile, LocalDateTime dateTime) {
        try {
            List<Weighing> list = this.repository.findByCreationDateBeforeAndProfileId(dateTime,
                    idProfile);
            return list.get(list.size() - 1);
        } catch (Exception e) {
            throw new IllegalArgumentException("Взвешивание не найдено");
        }
//        return null;
    }
}
