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

    public Guardian findGuardianById(Integer guardianId) {
        return this.guardianRepository.findById(guardianId);
    }
}
