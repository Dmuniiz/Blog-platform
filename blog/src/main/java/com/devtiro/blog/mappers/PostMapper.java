package com.devtiro.blog.mappers;

import com.devtiro.blog.domain.Post;
import com.devtiro.blog.domain.dtos.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "categoryDto", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

}
