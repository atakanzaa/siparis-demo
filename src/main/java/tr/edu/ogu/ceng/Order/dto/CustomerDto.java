package tr.edu.ogu.ceng.Order.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CustomerDto {
    private Long customerId;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private Integer version;
}
