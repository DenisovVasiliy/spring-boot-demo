package com.dva.demo.model.converters;

import com.dva.demo.model.Group;
import com.dva.demo.model.dto.GroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GroupConverter {

    public GroupDto toDto(Group group) {
        log.debug("toDto({})", group);
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setDeleted(group.isDeleted());
        return groupDto;
    }

    public Group toEntity(GroupDto groupDto) {
        log.debug("toEntity({})", groupDto);
        Group group = new Group();
        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setDeleted(groupDto.isDeleted());
        return group;
    }
}
