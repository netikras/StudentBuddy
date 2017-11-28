package com.netikras.studies.studentbuddy.core.validator;

import com.netikras.studies.studentbuddy.core.data.api.dao.JpaRepo;
import com.netikras.studies.studentbuddy.core.data.api.dao.ResourceRepoProvider;
import com.netikras.studies.studentbuddy.core.data.api.dao.RoleDao;
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
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
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
import com.netikras.studies.studentbuddy.core.data.sys.dao.UserDao;
import com.netikras.studies.studentbuddy.core.data.sys.model.PasswordRequirement;
import com.netikras.studies.studentbuddy.core.data.sys.model.ResourceActionLink;
import com.netikras.studies.studentbuddy.core.data.sys.model.Role;
import com.netikras.studies.studentbuddy.core.data.sys.model.SystemSetting;
import com.netikras.studies.studentbuddy.core.data.sys.model.User;
import com.netikras.tools.common.model.mapper.MappingSettings;
import com.netikras.tools.common.model.mapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;

import static com.netikras.tools.common.security.IntegrityUtils.isNullOrEmpty;

@Component
public class EntityProvider {

    @Resource
    private ResourceRepoProvider repositories;
    @Resource
    private ModelMapper modelMapper;

    private MappingSettings mappingSettings;

    @PostConstruct
    public void init() {
        mappingSettings = new MappingSettings()
                .setDepthMax(1)
                .setForceUpdate(true);
    }


    public Course fetch(CourseDto item) {
        return fetch(modelMapper.apply(new Course(), item, mappingSettings));
    }

    @Transactional
    public Course fetch(Course item) {
        Course existing = null;

        if (item == null) return existing;

        JpaRepo<Course> repo = repositories.getRepoForModel(item.getClass());
        if (repo == null) return existing;

        if (!isNullOrEmpty(item.getId())) {
            existing = repo.findOne(item.getId());
        }

        return existing;
    }


    public Lecture fetch(LectureDto item) {
        return fetch(modelMapper.apply(new Lecture(), item, mappingSettings));
    }

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

    public Assignment fetch(AssignmentDto item) {
        return fetch(modelMapper.apply(new Assignment(), item, mappingSettings));
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


    public Person fetch(PersonDto item) {
        return fetch(modelMapper.apply(new Person(), item, mappingSettings));
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


    public StudentsGroup fetch(StudentsGroupDto item) {
        return fetch(modelMapper.apply(new StudentsGroup(), item, mappingSettings));
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


    public DisciplineTest fetch(DisciplineTestDto item) {
        return fetch(modelMapper.apply(new DisciplineTest(), item, mappingSettings));
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


    public Student fetch(StudentDto item) {
        return fetch(modelMapper.apply(new Student(), item, mappingSettings));
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


    public User fetch(UserDto item) {
        return fetch(modelMapper.apply(new User(), item, mappingSettings));
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


    public LectureGuest fetch(LectureGuestDto item) {
        return fetch(modelMapper.apply(new LectureGuest(), item, mappingSettings));
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


    public Lecturer fetch(LecturerDto item) {
        return fetch(modelMapper.apply(new Lecturer(), item, mappingSettings));
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


    public Address fetch(AddressDto item) {
        return fetch(modelMapper.apply(new Address(), item, mappingSettings));
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


    public Building fetch(BuildingDto item) {
        return fetch(modelMapper.apply(new Building(), item, mappingSettings));
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


    public BuildingSection fetch(BuildingSectionDto item) {
        return fetch(modelMapper.apply(new BuildingSection(), item, mappingSettings));
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

    public BuildingFloor fetch(BuildingFloorDto item) {
        return fetch(modelMapper.apply(new BuildingFloor(), item, mappingSettings));
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

    public FloorLayout fetch(FloorLayoutDto item) {
        return fetch(modelMapper.apply(new FloorLayout(), item, mappingSettings));
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


    public LectureRoom fetch(LectureRoomDto item) {
        return fetch(modelMapper.apply(new LectureRoom(), item, mappingSettings));
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


    public Discipline fetch(DisciplineDto item) {
        return fetch(modelMapper.apply(new Discipline(), item, mappingSettings));
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


    public PersonnelMember fetch(PersonnelMemberDto item) {
        return fetch(modelMapper.apply(new PersonnelMember(), item, mappingSettings));
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


    public School fetch(SchoolDto item) {
        return fetch(modelMapper.apply(new School(), item, mappingSettings));
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


    public SchoolDepartment fetch(SchoolDepartmentDto item) {
        return fetch(modelMapper.apply(new SchoolDepartment(), item, mappingSettings));
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


    public Tag fetch(TagDto item) {
        return fetch(modelMapper.apply(new Tag(), item, mappingSettings));
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


    public Role fetch(RoleDto item) {
        return fetch(modelMapper.apply(new Role(), item, mappingSettings));
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


    public ResourceActionLink fetch(RolePermissionDto item) {
        return fetch(modelMapper.apply(new ResourceActionLink(), item, mappingSettings));
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


    public Comment fetch(CommentDto item) {
        return fetch(modelMapper.apply(new Comment(), item, mappingSettings));
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


    public SystemSetting fetch(SystemSettingDto item) {
        return fetch(modelMapper.apply(new SystemSetting(), item, mappingSettings));
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


    public PasswordRequirement fetch(PasswordRequirementDto item) {
        return fetch(modelMapper.apply(new PasswordRequirement(), item, mappingSettings));
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

}
