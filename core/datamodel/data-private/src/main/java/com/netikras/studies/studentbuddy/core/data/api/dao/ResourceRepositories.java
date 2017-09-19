package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Discipline;
import com.netikras.studies.studentbuddy.core.data.api.model.DisciplineTest;
import com.netikras.studies.studentbuddy.core.data.api.model.FloorLayout;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecture;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureGuest;
import com.netikras.studies.studentbuddy.core.data.api.model.LectureRoom;
import com.netikras.studies.studentbuddy.core.data.api.model.Lecturer;
import com.netikras.studies.studentbuddy.core.data.api.model.Person;
import com.netikras.studies.studentbuddy.core.data.api.model.PersonnelMember;
import com.netikras.studies.studentbuddy.core.data.api.model.School;
import com.netikras.studies.studentbuddy.core.data.api.model.SchoolDepartment;
import com.netikras.studies.studentbuddy.core.data.api.model.Student;
import com.netikras.studies.studentbuddy.core.data.api.model.StudentsGroup;
import com.netikras.studies.studentbuddy.core.data.api.model.Tag;
import com.netikras.studies.studentbuddy.core.data.sys.dao.PasswordRequirementsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.SettingsDao;
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.RolePermissions;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Component
public class ResourceRepositories {

    @Resource
    private ApplicationContext context;


    private static final Map<com.netikras.studies.studentbuddy.core.data.meta.Resource, Class<? extends JpaRepo>> RESOURCE_REPO_CLASS_MAP;

    static {
        RESOURCE_REPO_CLASS_MAP = new HashMap<>();
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURE, LectureDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.ASSIGNMENT, AssignmentDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSON, PersonDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT_GROUP, StudentsGroupDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.TEST, DisciplineTestDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT, StudentDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.USER, UserDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.GUEST, LectureGuestDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURER, LecturerDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.ADDRESS, AddressDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.BUILDING, BuildingDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.BUILDING_SECTION, BuildingSectionDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.FLOOR, FloorDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.FLOOR_MAP, FloorLayoutDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.ROOM, LectureRoomDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.DISCIPLINE, DisciplineDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSONNEL, PersonnelDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.SCHOOL, SchoolDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.SCHOOL_DEPARTMENT, SchoolDepartmentDao.class);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.NOTIFICATION, null);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM, null);
        RESOURCE_REPO_CLASS_MAP.put(com.netikras.studies.studentbuddy.core.data.meta.Resource._PARAM, null);
    }

    private static final Map<Class, Class<? extends JpaRepo>> MODEL_REPO_CLASS_MAP;

    static {
        MODEL_REPO_CLASS_MAP = new HashMap<>();
        MODEL_REPO_CLASS_MAP.put(Lecture.class, LectureDao.class);
        MODEL_REPO_CLASS_MAP.put(Assignment.class, AssignmentDao.class);
        MODEL_REPO_CLASS_MAP.put(Person.class, PersonDao.class);
        MODEL_REPO_CLASS_MAP.put(StudentsGroup.class, StudentsGroupDao.class);
        MODEL_REPO_CLASS_MAP.put(DisciplineTest.class, DisciplineTestDao.class);
        MODEL_REPO_CLASS_MAP.put(Student.class, StudentDao.class);
        MODEL_REPO_CLASS_MAP.put(User.class, UserDao.class);
        MODEL_REPO_CLASS_MAP.put(LectureGuest.class, LectureGuestDao.class);
        MODEL_REPO_CLASS_MAP.put(Lecturer.class, LecturerDao.class);
        MODEL_REPO_CLASS_MAP.put(Address.class, AddressDao.class);
        MODEL_REPO_CLASS_MAP.put(Building.class, BuildingDao.class);
        MODEL_REPO_CLASS_MAP.put(BuildingSection.class, BuildingSectionDao.class);
        MODEL_REPO_CLASS_MAP.put(BuildingFloor.class, FloorDao.class);
        MODEL_REPO_CLASS_MAP.put(FloorLayout.class, FloorLayoutDao.class);
        MODEL_REPO_CLASS_MAP.put(LectureRoom.class, LectureRoomDao.class);
        MODEL_REPO_CLASS_MAP.put(Discipline.class, DisciplineDao.class);
        MODEL_REPO_CLASS_MAP.put(PersonnelMember.class, PersonnelDao.class);
        MODEL_REPO_CLASS_MAP.put(School.class, SchoolDao.class);
        MODEL_REPO_CLASS_MAP.put(SchoolDepartment.class, SchoolDepartmentDao.class);
        MODEL_REPO_CLASS_MAP.put(Tag.class, TagDao.class);
        MODEL_REPO_CLASS_MAP.put(Role.class, RoleDao.class);
        MODEL_REPO_CLASS_MAP.put(RolePermissions.class, RolePermissionsDao.class);
        MODEL_REPO_CLASS_MAP.put(Comment.class, CommentDao.class);
        MODEL_REPO_CLASS_MAP.put(SystemSetting.class, SettingsDao.class);
        MODEL_REPO_CLASS_MAP.put(PasswordRequirement.class, PasswordRequirementsDao.class);
    }


    public JpaRepo getRepoForResource(com.netikras.studies.studentbuddy.core.data.meta.Resource resource) {
        if (resource == null) return null;
        Class repoClass = RESOURCE_REPO_CLASS_MAP.get(resource);
        if (repoClass == null) return null;

        JpaRepo repo = (JpaRepo) context.getBean(repoClass);
        return repo;
    }

    public JpaRepo getRepoForModel(Object model) {
        if (model == null) return null;
        return getRepoForModel(model.getClass());
    }

    public JpaRepo getRepoForModel(Class modelClass) {
        if (modelClass == null) return null;
        Class repoClass = MODEL_REPO_CLASS_MAP.get(modelClass);
        if (repoClass == null) return null;

        JpaRepo repo = (JpaRepo) context.getBean(repoClass);
        return repo;
    }

}
