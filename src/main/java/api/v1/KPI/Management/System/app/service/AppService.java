package api.v1.KPI.Management.System.app.service;


import api.v1.KPI.Management.System.app.dto.AppResponse;
import api.v1.KPI.Management.System.app.enums.AppLanguage;
import api.v1.KPI.Management.System.building.entity.BuildingEntity;
import api.v1.KPI.Management.System.building.repoisotry.BuildingRepository;
import api.v1.KPI.Management.System.category.entity.CategoryEntity;
import api.v1.KPI.Management.System.category.repository.CategoryRepository;
import api.v1.KPI.Management.System.department.entity.DepartmentEntity;
import api.v1.KPI.Management.System.department.repository.DepartmentRepository;
import api.v1.KPI.Management.System.exception.exps.AppBadException;
import api.v1.KPI.Management.System.offering.entity.OfferingEntity;
import api.v1.KPI.Management.System.offering.repository.OfferingRepository;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import api.v1.KPI.Management.System.profile.enums.ProfileRole;
import api.v1.KPI.Management.System.profile.repository.ProfileRepository;
import api.v1.KPI.Management.System.security.enums.GeneralStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bc;
    private String username = "Greed";
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OfferingRepository offeringRepository;


    public AppResponse<String> create(String id) {
        if (!id.equals("7d7a11c1364abd11509f29bcd158ec8b")){
            throw new AppBadException("Error");
        }
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(username);
        if (optional.isPresent()){
            throw new AppBadException("Error");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName("Avazbek");
        entity.setSurname("Yuldashev");
        entity.setUsername("telegram.vzlom@gmail.com");
        entity.setPassword(bc.encode("12345678A"));
        entity.setLanguage(AppLanguage.UZ);
        entity.setVisible(true);
        entity.setRole(ProfileRole.ROLE_OWNER);
        entity.setStatus(GeneralStatus.ACTIVE);
        ProfileEntity profile = profileRepository.save(entity);
//        createDepartment(profile.getId());
//        createAdmin();
        return new AppResponse("DONE");
    }


    public DepartmentEntity createDepartment(String id){
        ProfileEntity accaunt = new ProfileEntity();
        accaunt.setName("Avazbek");
        accaunt.setSurname("Yuldashev");
        accaunt.setUsername("telegram.vzlom@gmail.com");
        accaunt.setPassword(bc.encode("12345"));
        accaunt.setLanguage(AppLanguage.UZ);
        accaunt.setRole(ProfileRole.ROLE_USER);
        accaunt.setStatus(GeneralStatus.ACTIVE);
        accaunt.setVisible(true);
        profileRepository.save(accaunt);

        DepartmentEntity departmentEntity =  new DepartmentEntity();
        departmentEntity.setTitle("RTTM");
        departmentEntity.setDescription("Raqamli talim texnalogialar markazi");
        departmentEntity.setChiefId(id);
        DepartmentEntity department = departmentRepository.save(departmentEntity);

        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setTitle("Asosiy bino");
        buildingEntity.setDescription("Asosiy bino");
        buildingEntity.setChiefId(id);
        buildingEntity.setDepartmentId(department.getId());
        BuildingEntity bino1 = buildingRepository.save(buildingEntity);

        BuildingEntity buildingEntity1 = new BuildingEntity();
        buildingEntity1.setTitle("RTTM");
        buildingEntity1.setDescription("RTTM");
        buildingEntity1.setChiefId(id);
        buildingEntity1.setDepartmentId(department.getId());
        BuildingEntity bino2 = buildingRepository.save(buildingEntity1);

        BuildingEntity buildingEntity2 = new BuildingEntity();
        buildingEntity2.setTitle("RTTM");
        buildingEntity2.setDescription("RTTM");
        buildingEntity2.setChiefId(id);
        buildingEntity2.setDepartmentId(department.getId());
        BuildingEntity bino3 = buildingRepository.save(buildingEntity2);


        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setDepartmentId(department.getId());
        categoryEntity1.setBuildingId(bino1.getId());
        categoryEntity1.setTitle("RTTM1");
        categoryEntity1.setDescription("RTTM1");
        CategoryEntity category1 = categoryRepository.save(categoryEntity1);


        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setDepartmentId(department.getId());
        categoryEntity2.setBuildingId(bino2.getId());
        categoryEntity2.setTitle("RTTM2");
        categoryEntity2.setDescription("RTTM2");
        CategoryEntity category2 = categoryRepository.save(categoryEntity2);


        CategoryEntity categoryEntity3 = new CategoryEntity();
        categoryEntity3.setDepartmentId(department.getId());
        categoryEntity3.setBuildingId(bino3.getId());
        categoryEntity3.setTitle("RTTM3");
        categoryEntity3.setDescription("RTTM3");
        CategoryEntity category3 = categoryRepository.save(categoryEntity3);


        OfferingEntity offering1 =  new OfferingEntity();
        offering1.setTitle("RTTM");
        offering1.setDescription("RTTM");
        offering1.setDepartmentId(department.getId());
        offering1.setCategoryId(category1.getId());
        OfferingEntity offeringEntity1 = offeringRepository.save(offering1);

        OfferingEntity offering2 =  new OfferingEntity();
        offering2.setTitle("RTTM");
        offering2.setDescription("RTTM");
        offering2.setDepartmentId(department.getId());
        offering2.setCategoryId(category2.getId());
        OfferingEntity offeringEntity2 = offeringRepository.save(offering2);


        OfferingEntity offering3 =  new OfferingEntity();
        offering3.setTitle("RTTM");
        offering3.setDescription("RTTM");
        offering3.setDepartmentId(department.getId());
        offering3.setCategoryId(category3.getId());
        OfferingEntity offeringEntit31 = offeringRepository.save(offering3);
        return null;
    }

    public void createAdmin(){

        ProfileEntity entity1 = new ProfileEntity();
        entity1.setName("Avazbek");
        entity1.setSurname("Yuldashev");
        entity1.setUsername("avazbek1@gmail.com");
        entity1.setStatus(GeneralStatus.ACTIVE);
        entity1.setRole(ProfileRole.ROLE_OWNER);
        entity1.setPassword(bc.encode("123456"));
        entity1.setLanguage(AppLanguage.UZ);
        entity1.setLanguage(AppLanguage.UZ);
        entity1.setVisible(true);
        profileRepository.save(entity1);

        ProfileEntity entity2 = new ProfileEntity();
        entity2.setName("Avazbek");
        entity2.setSurname("Yuldashev");
        entity2.setUsername("avazbek2@gmail.com");
        entity2.setStatus(GeneralStatus.ACTIVE);
        entity2.setRole(ProfileRole.ROLE_MANAGER);
        entity2.setPassword(bc.encode("123456"));
        entity2.setLanguage(AppLanguage.UZ);
        entity2.setLanguage(AppLanguage.UZ);
        entity2.setVisible(true);
        profileRepository.save(entity2);

        ProfileEntity entity3 = new ProfileEntity();
        entity3.setName("Avazbek");
        entity3.setSurname("Yuldashev");
        entity3.setUsername("avazbek3@gmail.com");
        entity3.setStatus(GeneralStatus.ACTIVE);
        entity3.setRole(ProfileRole.ROLE_ADMIN);
        entity3.setPassword(bc.encode("123456"));
        entity3.setLanguage(AppLanguage.UZ);
        entity3.setLanguage(AppLanguage.UZ);
        entity3.setVisible(true);
        profileRepository.save(entity3);

        ProfileEntity entity4 = new ProfileEntity();
        entity4.setName("Avazbek");
        entity4.setSurname("Yuldashev");
        entity4.setUsername("avazbek4@gmail.com");
        entity4.setStatus(GeneralStatus.ACTIVE);
        entity4.setRole(ProfileRole.ROLE_EMPLOYEE);
        entity4.setPassword(bc.encode("123456"));
        entity4.setLanguage(AppLanguage.UZ);
        entity4.setLanguage(AppLanguage.UZ);
        entity4.setVisible(true);
        profileRepository.save(entity4);
        ProfileEntity entity5 = new ProfileEntity();
        entity5.setName("Avazbek");
        entity5.setSurname("Yuldashev");
        entity5.setUsername("avazbek5@gmail.com");
        entity5.setStatus(GeneralStatus.ACTIVE);
        entity5.setRole(ProfileRole.ROLE_USER);
        entity5.setPassword(bc.encode("123456"));
        entity5.setLanguage(AppLanguage.UZ);
        entity5.setLanguage(AppLanguage.UZ);
        entity5.setVisible(true);
        profileRepository.save(entity5);
    }

}