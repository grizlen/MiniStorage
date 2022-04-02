package ru.griz.ms.server.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO {
    private Long depId;
    private Long subDepId;
    private String depName;
    private String subDepName;

    public static DepartmentDTOBuilder builder() {
        return new DepartmentDTOBuilder();
    }

    public static class DepartmentDTOBuilder {

        private final DepartmentDTO dto;

        private DepartmentDTOBuilder() {
            dto = new DepartmentDTO();
        }

        public DepartmentDTOBuilder departmentId(Long departmentId) {
            dto.depId = departmentId;
            return this;
        }

        public DepartmentDTOBuilder departmentName(String departmentName) {
            dto.depName = departmentName;
            return this;
        }

        public DepartmentDTOBuilder subDepartmentId(Long subDepartmentId) {
            dto.subDepId = subDepartmentId;
            return this;
        }

        public DepartmentDTOBuilder subDepartmentName(String subDepartmentName) {
            dto.subDepName = subDepartmentName;
            return this;
        }

        public DepartmentDTO build() {
            return dto;
        }
    }
}
