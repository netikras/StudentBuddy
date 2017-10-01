package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.JpaRepo;
import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.studies.studentbuddy.core.data.api.dao.RoleDao;
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
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.lang.reflect.Field;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Component
public class EntityProvider {

    @Resource
    private ResourceRepoProvider repositories;


    @Transactional
    public Lecture fetch(Lecture item) {
        Lecture existing = null;

        if (item == null) return existing;

        JpaRepo<Lecture> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Assignment fetch(Assignment item) {
        Assignment existing = null;

        if (item == null) return existing;

        JpaRepo<Assignment> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Person fetch(Person item) {
        Person existing = null;

        if (item == null) return existing;

        JpaRepo<Person> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public StudentsGroup fetch(StudentsGroup item) {
        StudentsGroup existing = null;

        if (item == null) return existing;

        JpaRepo<StudentsGroup> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public DisciplineTest fetch(DisciplineTest item) {
        DisciplineTest existing = null;

        if (item == null) return existing;

        JpaRepo<DisciplineTest> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Student fetch(Student item) {
        Student existing = null;

        if (item == null) return existing;

        JpaRepo<Student> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public User fetch(User item) {
        User existing = null;

        if (item == null) return existing;

        JpaRepo<User> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;
        UserDao dao = (UserDao) repo;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        if (existing == null && !isNullOrEmpty(item.getName())) {
            existing = dao.findByName(item.getName());
        }

        return existing;
    }

    @Transactional
    public LectureGuest fetch(LectureGuest item) {
        LectureGuest existing = null;

        if (item == null) return existing;

        JpaRepo<LectureGuest> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Lecturer fetch(Lecturer item) {
        Lecturer existing = null;

        if (item == null) return existing;

        JpaRepo<Lecturer> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Address fetch(Address item) {
        Address existing = null;

        if (item == null) return existing;

        JpaRepo<Address> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Building fetch(Building item) {
        Building existing = null;

        if (item == null) return existing;

        JpaRepo<Building> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public BuildingSection fetch(BuildingSection item) {
        BuildingSection existing = null;

        if (item == null) return existing;

        JpaRepo<BuildingSection> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public BuildingFloor fetch(BuildingFloor item) {
        BuildingFloor existing = null;

        if (item == null) return existing;

        JpaRepo<BuildingFloor> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public FloorLayout fetch(FloorLayout item) {
        FloorLayout existing = null;

        if (item == null) return existing;

        JpaRepo<FloorLayout> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public LectureRoom fetch(LectureRoom item) {
        LectureRoom existing = null;

        if (item == null) return existing;

        JpaRepo<LectureRoom> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Discipline fetch(Discipline item) {
        Discipline existing = null;

        if (item == null) return existing;

        JpaRepo<Discipline> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public PersonnelMember fetch(PersonnelMember item) {
        PersonnelMember existing = null;

        if (item == null) return existing;

        JpaRepo<PersonnelMember> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public School fetch(School item) {
        School existing = null;

        if (item == null) return existing;

        JpaRepo<School> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public SchoolDepartment fetch(SchoolDepartment item) {
        SchoolDepartment existing = null;

        if (item == null) return existing;

        JpaRepo<SchoolDepartment> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Tag fetch(Tag item) {
        Tag existing = null;

        if (item == null) return existing;

        JpaRepo<Tag> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Role fetch(Role item) {
        Role existing = null;

        if (item == null) return existing;

        JpaRepo<Role> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;
        RoleDao dao = (RoleDao) repo;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        if (existing == null && !isNullOrEmpty(item.getName())) {
            existing = dao.findByName(item.getName());
        }

        return existing;
    }

    @Transactional
    public ResourceActionLink fetch(ResourceActionLink item) {
        ResourceActionLink existing = null;

        if (item == null) return existing;

        JpaRepo<ResourceActionLink> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public Comment fetch(Comment item) {
        Comment existing = null;

        if (item == null) return existing;

        JpaRepo<Comment> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public SystemSetting fetch(SystemSetting item) {
        SystemSetting existing = null;

        if (item == null) return existing;

        JpaRepo<SystemSetting> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }

    @Transactional
    public PasswordRequirement fetch(PasswordRequirement item) {
        PasswordRequirement existing = null;

        if (item == null) return existing;

        JpaRepo<PasswordRequirement> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }


    public <T> void prepForUpdate(T original, T updated) {
        if (original == null) return;
        if (updated == null) return;

        for (Field field : original.getClass().getDeclaredFields()) {
            Class ftype = field.getType();

            if (ftype.isPrimitive()
                    || String.class == ftype
                    || Enum.class == ftype
                    || Boolean.class == ftype
                    || Character.class == ftype
                    || Number.class == ftype
                    ) {
                continue;
            }
            try {
                field.setAccessible(true);
                field.set(updated, field.get(original));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }


}
