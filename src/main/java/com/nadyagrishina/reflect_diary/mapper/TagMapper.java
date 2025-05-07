package com.nadyagrishina.reflect_diary.mapper;

import com.nadyagrishina.reflect_diary.DTO.TagDTO;
import com.nadyagrishina.reflect_diary.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public Tag toEntity(TagDTO dto) {
        Tag tag = new Tag();
        tag.setName(dto.getName());
        return tag;
    }

    public TagDTO toDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        return tagDTO;
    }
}
