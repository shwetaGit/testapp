package com.app.server.repository.appbasicsetup.userrolemanagement;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Query;
import java.lang.Override;

@Repository
@Transactional
public class MenuRepositoryImpl implements MenuRepository {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    @Override
    public List<String> getHeaderByUser(String userId, Integer appType) throws Exception {
        try {
            final javax.persistence.EntityManager entityManager = emfResource.getResource();
            final String JPQL = "SELECT menu.menuTreeId FROM AppMenus menu WHERE menu.systemInfo.activeStatus =:activeStatus AND menu.expiryDate >= :currentTime AND menu.menuHead =:headMenu AND menu.appType =:appType AND menu.menuId IN(SELECT DISTINCT(roleMenu.menuId) FROM RoleMenuBridge roleMenu, UserRoleBridge userRole WHERE roleMenu.roles.roleId = userRole.roleId AND userRole.userId =:userId) order by menu.menuTreeId";
            final Query query = entityManager.createQuery(JPQL);
            query.setParameter("appType", appType);
            query.setParameter("userId", userId);
            query.setParameter("headMenu", true);
            query.setParameter("activeStatus", 1);
            query.setParameter("currentTime", new Timestamp(System.currentTimeMillis()));
            final List<String> menu = query.getResultList();
            return menu;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Object[]> getSubMenus(String header, String userId, Integer appType) throws Exception {
        try {
            final javax.persistence.EntityManager entityManager = emfResource.getResource();
            final String JPQL = "SELECT menu.menuId, menu.menuTreeId, menu.menuLabel, menu.menuAction, menu.menuIcon, menu.menuCommands, menu.refObjectId, menu.autoSave FROM AppMenus menu WHERE menu.systemInfo.activeStatus =:activeStatus AND menu.expiryDate >= :currentTime AND menu.appType =:appType AND menu.menuTreeId LIKE :header AND menu.menuId IN(SELECT DISTINCT(roleMenu.menuId) FROM RoleMenuBridge roleMenu, UserRoleBridge userRole WHERE roleMenu.roles.roleId = userRole.roleId AND userRole.userId =:userId) order by menu.menuTreeId";
            final Query query = entityManager.createQuery(JPQL);
            query.setParameter("appType", appType);
            query.setParameter("header", header + "%");
            query.setParameter("activeStatus", 1);
            query.setParameter("userId", userId);
            query.setParameter("currentTime", new Timestamp(System.currentTimeMillis()));
            final List<java.lang.Object[]> result = query.getResultList();
            return result;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<String> getHeaderByUser(String userId) throws Exception {
        try {
            final javax.persistence.EntityManager entityManager = emfResource.getResource();
            final String JPQL = "SELECT menu.menuTreeId FROM AppMenus menu WHERE menu.systemInfo.activeStatus =:activeStatus AND menu.menuHead =:headMenu AND menu.menuId IN(SELECT DISTINCT(roleMenu.menuId) FROM RoleMenuBridge roleMenu, UserRoleBridge userRole WHERE roleMenu.roles.roleId = userRole.roleId AND userRole.userId =:userId) order by menu.menuTreeId";
            final Query query = entityManager.createQuery(JPQL);
            query.setParameter("userId", userId);
            query.setParameter("headMenu", true);
            query.setParameter("activeStatus", 1);
            final List<String> menu = query.getResultList();
            return menu;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Object[]> getSubMenus(String header, String userId) throws Exception {
        try {
            final javax.persistence.EntityManager entityManager = emfResource.getResource();
            final String JPQL = "SELECT menu.menuId, menu.menuTreeId, menu.menuLabel, menu.menuAction, menu.menuIcon, menu.menuCommands, menu.refObjectId, menu.autoSave, menu.appType FROM AppMenus menu WHERE menu.systemInfo.activeStatus =:activeStatus AND menu.menuTreeId LIKE :header AND menu.menuId IN(SELECT DISTINCT(roleMenu.menuId) FROM RoleMenuBridge roleMenu, UserRoleBridge userRole WHERE roleMenu.roles.roleId = userRole.roleId AND userRole.userId =:userId) order by menu.menuTreeId";
            final Query query = entityManager.createQuery(JPQL);
            query.setParameter("header", header + "%");
            query.setParameter("activeStatus", 1);
            query.setParameter("userId", userId);
            final List<Object[]> result = query.getResultList();
            return result;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
