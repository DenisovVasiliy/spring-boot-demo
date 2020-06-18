package com.dva.demo.services.implementation;

import com.dva.demo.model.Group;
import com.dva.demo.model.converters.GroupConverter;
import com.dva.demo.model.converters.StudentConverter;
import com.dva.demo.model.dto.GroupDto;
import com.dva.demo.repository.GroupRepository;
import com.dva.demo.services.GroupService;
import com.dva.demo.services.exceptions.OperationCancelledException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupConverter groupConverter;
    private final StudentConverter studentConverter;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
                            GroupConverter groupConverter,
                            StudentConverter studentConverter) {
        this.groupRepository = groupRepository;
        this.groupConverter = groupConverter;
        this.studentConverter = studentConverter;
    }

    @Override
    public List<GroupDto> findAll() {
        log.debug("findAll()");
        return groupRepository.findAll()
                .stream().map(groupConverter::toDto).collect(toList());
    }

    @Override
    public List<GroupDto> findMarked(boolean deleted) {
        log.debug("findMarked({})", deleted);
        return groupRepository.findMarked(deleted)
                .stream().map(groupConverter::toDto).collect(toList());
    }

    @Override
    public GroupDto findById(Long id) {
        log.debug("findById({})", id);
        Optional<Group> group = groupRepository.findById(id);
        if (group.isPresent()) {
            log.debug("Found {}", group.get());
            GroupDto groupDto = groupConverter.toDto(group.get());
            groupDto.setStudents(group.get().getStudents().stream().filter(s -> !s.isDeleted())
                    .map(studentConverter::toDto).collect(toList()));
            return groupDto;
        }
        log.warn("Group(id = {}) not found.", id);
        return null;
    }

    @Override
    public GroupDto save(GroupDto groupDto) {
        log.debug("save({})", groupDto);
        return groupConverter.toDto(groupRepository.save(groupConverter.toEntity(groupDto)));
    }

    @Override
    public void delete(Long id) {
        log.debug("delete({})", id);
        log.debug("findById({}) before deletion.", id);
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            log.debug("Check group for students in it.");
            if (group.getStudents().stream().noneMatch(student -> !student.isDeleted())) {
                log.debug("Group is empty. markDeleted({})", group);
                groupRepository.markDeleted(group);
            } else {
                log.warn("Deletion cancelled: {} contains students", group);
                throw new OperationCancelledException(format("Deletion cancelled: %s contains students", group));
            }
        } else {
            log.warn("Group(id = {}) not found.", id);
            throw new EntityNotFoundException(format("Group(id = %s) not found.", id));
        }
    }
}
