package com.dva.demo.model.converters;

import com.dva.demo.model.Group;
import com.dva.demo.model.dto.GroupDto;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter {

    public GroupDto toDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setDeleted(group.isDeleted());
        return groupDto;
    }

    public Group toEntity(GroupDto groupDto) {
        Group group = new Group();
        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setDeleted(groupDto.isDeleted());
        return group;
    }
}
