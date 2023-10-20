package co.edu.unisabana.Gribu.Services;

import co.edu.unisabana.Gribu.Entities.Label;
import co.edu.unisabana.Gribu.Repositories.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LabelService {
    @Autowired
    LabelRepository labelRepository;

    public List<Label> getLabels() {
        return labelRepository.findAll();
    }

    public Optional<Label> getLabel(Long id) {
        return labelRepository.findById(id);
    }

    public void saveOrUpdate(Label label) {
        labelRepository.save(label);
    }
    public void delete(Long id) {
        labelRepository.deleteById(id);
    }

}
