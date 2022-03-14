package com.clone.instagram.web.dto.post;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Data
public class PostUploadDto {

    private String text;
    private String tag;
}
