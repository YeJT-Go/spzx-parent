package com.y1jt.spzxmodel.entity.product;

import com.y1jt.spzxmodel.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "品牌实体类")
public class Brand extends BaseEntity {

	@Schema(description = "品牌名称")
	private String name;

	@Schema(description = "品牌logo")
	private String logo;

}
