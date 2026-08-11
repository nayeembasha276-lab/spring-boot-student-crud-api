package com.Student.Project.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto {
    private Long id;
    private String rollNo;
    private String name;
    private String email;
    private String course;
    private String mobile;


}
