package tr.edu.ogu.ceng.Order.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Order_ItemsDto {
    private Long orderItemId;
    private Integer quantity;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private Integer version;
}
