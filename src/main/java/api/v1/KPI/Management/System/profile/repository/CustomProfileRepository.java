package api.v1.KPI.Management.System.profile.repository;


import api.v1.KPI.Management.System.app.dto.FilterResultDTO;
import api.v1.KPI.Management.System.profile.dto.owner.ProfileOwnerFilterDTO;
import api.v1.KPI.Management.System.profile.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomProfileRepository {
    @Autowired
    private EntityManager entityManager;

    public FilterResultDTO<ProfileEntity> filter(ProfileOwnerFilterDTO dto, Integer page, Integer size) {
        StringBuilder queryBuilder = new StringBuilder(" WHERE p.visible = true ");
        Map<String, Object> params = new HashMap<>();

        if (dto.getName() != null) {
            queryBuilder.append(" AND lower(p.name) like :nameQuery ");
            params.put("nameQuery", "%" + dto.getName().toLowerCase() + "% ");
        }

        if (dto.getSurname() != null) {
            queryBuilder.append(" AND lower(p.surname) like :surnameQuery ");
            params.put("surnameQuery", "%" + dto.getSurname().toLowerCase() + "% ");
        }
        if (dto.getUsername() != null) {
            queryBuilder.append(" AND lower(p.username) like :usernameQuery ");
            params.put("usernameQuery", "%" + dto.getUsername().toLowerCase() + "% ");
        }



        StringBuilder selectBuilder = new StringBuilder("SELECT p FROM ProfileEntity as p ").
                append(queryBuilder).
                append(" ORDER BY p.createdDate DESC ");

        StringBuilder countBuilder = new StringBuilder("SELECT COUNT(p) FROM ProfileEntity as p").
                append(queryBuilder);



        ///  select query
        Query selectQuery = entityManager.createQuery(selectBuilder.toString());
        selectQuery.setFirstResult(page * size);   // 50
        selectQuery.setMaxResults(size);           // 30
        params.forEach(selectQuery::setParameter);
        List<ProfileEntity> list = selectQuery.getResultList();

        ///  count query
        Query countQuery = entityManager.createQuery(countBuilder.toString());

        params.forEach(countQuery::setParameter);
        Long count = (Long) countQuery.getSingleResult();

        return new FilterResultDTO<>(list, count);
    }
}
