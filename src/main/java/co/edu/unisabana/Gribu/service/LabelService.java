package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.exception.ExistingResourceException;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelService {
    @Autowired
    LabelRepository labelRepository;
    public List<Label> getLabels() {
        if (labelRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No se encontraron etiquetas");
        }else {
            return labelRepository.findAll();
        }
    }

    public Label getLabelByName(String name) {
        if (labelRepository.findByNameIgnoreCase(name)==null){
            throw new ResourceNotFoundException("La etiqueta con el nombre= "+name+" no existe");
        }else {
            return labelRepository.findByNameIgnoreCase(name);
        }

    }
    public void saveLabel(Label label) {
        if (labelRepository.findAll().isEmpty()) {
            labelRepository.save(label);
        }else if (labelRepository.findByNameIgnoreCase(label.getName())!=null){
            throw new ExistingResourceException("La etiqueta que intenta crear ya existe");
        }else {
            labelRepository.save(label);
        }
    }
    public void updateLabel(Label label) {
        if (labelRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No hay etiquetas para modificar");
        }else if (labelRepository.findByNameIgnoreCase(label.getName())!=null){
            throw new ExistingResourceException("La etiqueta que intenta modificar no existe");
        }else {
            labelRepository.save(label);
        }
    }
    public Optional<Label> getLabelById(Long id) {
        if (labelRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("La etiqueta con el id= " + id + " no existe");
        } else {
            return labelRepository.findById(id);
        }
    }

    public void deleteLabelById(Long id) {
        if (labelRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("La etiqueta con el id= "+id+", no existe.");
        }else {
            labelRepository.deleteById(id);
        }
    }

        public void deleteLabelByName(String name) {
        if (labelRepository.findByNameIgnoreCase(name)==null){
            throw new ResourceNotFoundException("Etiqueta con el nombre= "+name+", no existe.");
        }else {
            labelRepository.deleteById(labelRepository.findByNameIgnoreCase(name).getId());
        }
    }


    public List<Lesson> getLessonsByLabel(Label label){
        if (labelRepository.findById(label.getId()).isEmpty()){
            throw new ResourceNotFoundException("Etiqueta con el nombre= "+label.getName()+", no existe.");
        }else if (label.getLessons().isEmpty()){
            throw new ResourceNotFoundException("No hay lecciones con la etiqueta= "+label.getName());
        }else {
            return label.getLessons();
        }
    }
}
