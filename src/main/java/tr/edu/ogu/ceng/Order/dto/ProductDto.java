package tr.edu.ogu.ceng.Order.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
    private Integer version;
}


