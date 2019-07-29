package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.entity.Profile;
import com.mangalaxy.mango.model.entity.Talent;
import com.mangalaxy.mango.repository.ProfileRepository;
import com.mangalaxy.mango.repository.TalentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TalentServiceImpl implements CrudService<Talent>, TalentService{

  private final TalentRepository talentRepository;
  private final ProfileRepository profileRepository;

  @Override
  public Talent getByid(Long id) {
    return talentRepository.findById(id).orElse(null);
  }

  @Override
  public List<Talent> getAll() {
    return talentRepository.findAll();
  }

  @Override
  public Page<Talent> findAll(Pageable pageable) {
    return talentRepository.findAll(pageable);
  }

  @Override
  public Profile getProfileByTalent(Long id) {
    Talent talent = talentRepository.findById(id).orElse(null);
    if (talent != null) {
      return profileRepository.findByOwner(talent);
    }

    return null;
  }

  @Override
  public Talent create(Talent talent) {
    return talentRepository.save(talent);
  }

  @Override
  public Talent update(Talent talent, Long id) {
    talent.setId(id);
    return talentRepository.save(talent);
  }

  @Override
  public Talent delete(Long id) {
    Talent talent = talentRepository.findById(id).orElse(null);
    if (talent != null) {
      talentRepository.delete(talent);
      return talent;
    }
    return null;
  }

}
