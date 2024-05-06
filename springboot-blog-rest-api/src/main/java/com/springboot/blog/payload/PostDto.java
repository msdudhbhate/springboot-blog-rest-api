package com.springboot.blog.payload;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(
		description = "PostDto model information"
		)
public class PostDto {
    private long id;

    // title should not be null  or empty
    // title should have at least 2 characters
    @Schema(description = "Blog post title")
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // post description should be not null or empty
    // post description should have at least 10 characters
    @Schema(description = "Blog post description")
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    // post content should not be null or empty
    @Schema(description = "Blog post content")
    @NotEmpty
    private String content;
    @Schema(description = "Blog post comments")
    private Set<CommentDto> comments;
    @Schema(description = "Blog post categoryId")
    private Long CategoryId;
}
