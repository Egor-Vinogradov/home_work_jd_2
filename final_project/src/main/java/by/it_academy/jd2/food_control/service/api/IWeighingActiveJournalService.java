package by.it_academy.jd2.food_control.service.api;

import by.it_academy.jd2.food_control.dto.WeighingActiveJournalDto;
import by.it_academy.jd2.food_control.dto.search.SearchFilter;
import by.it_academy.jd2.food_control.model.Profile;

import java.util.List;

public interface IWeighingActiveJournalService<T> {
    T addItem(Long idProfile, WeighingActiveJournalDto dto);
    T getItem(Long idProfile, Long id);
    List<T> getAllItems(Long idProfile, SearchFilter filter);
    T updateItem(Long idProfile, Long id, Long version, T item);
    boolean deleteItem(Long idProfile, Long id, Long version);
    Profile chekProfile(Long idProfile, Long verifiableId);
}
