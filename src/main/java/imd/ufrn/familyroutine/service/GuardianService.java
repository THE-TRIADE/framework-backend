package imd.ufrn.familyroutine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imd.ufrn.familyroutine.model.Guardian;
import imd.ufrn.familyroutine.repository.GuardianRepository;

@Service
public class GuardianService {
    @Autowired
    private GuardianRepository guardianRepository;

    public List<Guardian> findAll() {
        return this.guardianRepository.findAll();
    }

    public Guardian findGuardianById(Long guardianId) {
        return this.guardianRepository.findById(guardianId);
    }

    public void deleteGuardianById(Long guardianId) {
        this.guardianRepository.deleteById(guardianId);
    }

    public void deleteAllGuardians() {
        this.guardianRepository.deleteAll();
    }

    public Guardian createGuardian(Guardian newGuardian) {
        return this.guardianRepository.save(newGuardian);
    }
}
