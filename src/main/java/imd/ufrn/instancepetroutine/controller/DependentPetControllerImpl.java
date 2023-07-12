package imd.ufrn.instancepetroutine.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.controller.DependentController;
import imd.ufrn.instancepetroutine.model.DependentPet;

@CrossOrigin
@RestController
@RequestMapping("/dependent")
public class DependentPetControllerImpl extends DependentController<DependentPet> {
}
