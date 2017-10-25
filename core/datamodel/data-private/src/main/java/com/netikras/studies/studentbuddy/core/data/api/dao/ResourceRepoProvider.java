package com.netikras.studies.studentbuddy.core.data.api.dao;

import com.netikras.studies.studentbuddy.core.data.api.dto.PersonDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.AddressDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingSectionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.FloorLayoutDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.LectureRoomDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.CommentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.PasswordRequirementDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RoleDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.RolePermissionDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.SystemSettingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.TagDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.meta.UserDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.AssignmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.CourseDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.DisciplineTestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LectureGuestDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.LecturerDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.PersonnelMemberDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDepartmentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.SchoolDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.school.StudentsGroupDto;
import com.netikras.studies.studentbuddy.core.data.api.model.Address;
import com.netikras.studies.studentbuddy.core.data.api.model.Assignment;
import com.netikras.studies.studentbuddy.core.data.api.model.Building;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingSection;
import com.netikras.studies.studentbuddy.core.data.api.model.Comment;
import com.netikras.studies.studentbuddy.core.data.api.model.Course;
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
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ADDRESS;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ASSIGNMENT;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.BUILDING;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.BUILDING_SECTION;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.COURSE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.DISCIPLINE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.FLOOR;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.FLOOR_MAP;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.GUEST;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURE;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.LECTURER;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSON;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.PERSONNEL;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ROLE_PERMISSIONS;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.ROOM;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SCHOOL;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SCHOOL_DEPARTMENT;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.STUDENT_GROUP;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_PWREQ;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.SYSTEM_SETTING;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.TEST;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.USER;
import static com.netikras.studies.studentbuddy.core.data.meta.Resource.valueOf;
import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;


@Component
public class ResourceRepoProvider {

    @Resource
    private ApplicationContext context;


    private static final Map<com.netikras.studies.studentbuddy.core.data.meta.Resource, Class<? extends JpaRepo>> RESOURCE_REPO_CLASS_MAP;
    private static final Map<Class, Class<? extends JpaRepo>> MODEL_REPO_CLASS_MAP;
    private static final Map<Class, Class> MODEL_DTO_TYPE_MAP;

    static {
        MODEL_DTO_TYPE_MAP = new HashMap<>();
        MODEL_REPO_CLASS_MAP = new HashMap<>();
        RESOURCE_REPO_CLASS_MAP = new HashMap<>();

        addMapping(ASSIGNMENT, Assignment.class, AssignmentDao.class, AssignmentDto.class);
        addMapping(TEST, DisciplineTest.class, DisciplineTestDao.class, DisciplineTestDto.class);
        addMapping(PERSON, Person.class, PersonDao.class, PersonDto.class);
        addMapping(USER, User.class, UserDao.class, UserDto.class);
        addMapping(STUDENT_GROUP, StudentsGroup.class, StudentsGroupDao.class, StudentsGroupDto.class);
        addMapping(STUDENT, Student.class, StudentDao.class, StudentDto.class);
        addMapping(LECTURER, Lecturer.class, LecturerDao.class, LecturerDto.class);
        addMapping(GUEST, LectureGuest.class, LectureGuestDao.class, LectureGuestDto.class);

        addMapping(LECTURE, Lecture.class, LectureDao.class, LectureDto.class);
        addMapping(COURSE, Course.class, CourseDao.class, CourseDto.class);
        addMapping(DISCIPLINE, Discipline.class, DisciplineDao.class, DisciplineDto.class);
        addMapping(SCHOOL_DEPARTMENT, SchoolDepartment.class, SchoolDepartmentDao.class, SchoolDepartmentDto.class);
        addMapping(SCHOOL, School.class, SchoolDao.class, SchoolDto.class);

        addMapping(PERSONNEL, PersonnelMember.class, PersonnelDao.class, PersonnelMemberDto.class);

        addMapping(ADDRESS, Address.class, AddressDao.class, AddressDto.class);
        addMapping(BUILDING, Building.class, BuildingDao.class, BuildingDto.class);
        addMapping(BUILDING_SECTION, BuildingSection.class, BuildingSectionDao.class, BuildingSectionDto.class);
        addMapping(FLOOR, FloorDao.class, FloorDao.class, BuildingFloorDto.class);
        addMapping(FLOOR_MAP, FloorLayout.class, FloorLayoutDao.class, FloorLayoutDto.class);
        addMapping(ROOM, LectureRoom.class, LectureRoomDao.class, LectureRoomDto.class);

        addMapping(Comment.class, CommentDao.class, CommentDto.class);
        addMapping(Tag.class, TagDao.class, TagDto.class);

        addMapping(Role.class, RoleDao.class, RoleDto.class);
        addMapping(ROLE_PERMISSIONS, ResourceActionLink.class, RolePermissionDao.class, RolePermissionDto.class);

        addMapping(SYSTEM_SETTING, SystemSetting.class, SettingsDao.class, SystemSettingDto.class);
        addMapping(SYSTEM_PWREQ, PasswordRequirement.class, PasswordRequirementsDao.class, PasswordRequirementDto.class);
    }

    private static final void addMapping(com.netikras.studies.studentbuddy.core.data.meta.Resource resource, Class modelType, Class daoType, Class dtoType) {
        RESOURCE_REPO_CLASS_MAP.put(resource, daoType);
        MODEL_REPO_CLASS_MAP.put(modelType, daoType);
        MODEL_DTO_TYPE_MAP.put(modelType, dtoType);
    }

    private static final void addMapping(Class modelType, Class daoType, Class dtoType) {
        MODEL_REPO_CLASS_MAP.put(modelType, daoType);
        MODEL_DTO_TYPE_MAP.put(modelType, dtoType);
    }


    public Class getDtoTypeForResource(String resourceName) {
        if (isNullOrEmpty(resourceName)) {
            return null;
        }
        resourceName = resourceName.toUpperCase();
        return getDtoTypeForModel(getTypeForResource(resourceName));
    }

    public Class getDtoTypeForModel(Class modelType) {
        if (modelType == null) {
            return null;
        }
        return MODEL_DTO_TYPE_MAP.get(modelType);
    }

    public Class getModelTypeForDto(Class dtoType) {
        if (dtoType == null) {
            return null;
        }
        for (Entry<Class, Class> entry : MODEL_DTO_TYPE_MAP.entrySet()) {
            if (entry.getValue().isAssignableFrom(dtoType)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Class getTypeForResource(String resourceName) {
        Class repoClass = RESOURCE_REPO_CLASS_MAP.get(valueOf(resourceName.toUpperCase()));
        if (repoClass != null) {
            for (Entry<Class, Class<? extends JpaRepo>> classClassEntry : MODEL_REPO_CLASS_MAP.entrySet()) {
                if (classClassEntry.getValue().equals(repoClass)) {
                    return classClassEntry.getKey();
                }
            }
        }
        return null;
    }

    public JpaRepo getRepoForResource(com.netikras.studies.studentbuddy.core.data.meta.Resource resource) {
        if (resource == null) return null;
        Class repoClass = RESOURCE_REPO_CLASS_MAP.get(resource);
        if (repoClass == null) return null;

        JpaRepo repo = (JpaRepo) context.getBean(repoClass);
        return repo;
    }

    public JpaRepo getRepoForResource(String resourceName) {
        if (isNullOrEmpty(resourceName)) return null;
        return getRepoForResource(valueOf(resourceName.toUpperCase()));
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
