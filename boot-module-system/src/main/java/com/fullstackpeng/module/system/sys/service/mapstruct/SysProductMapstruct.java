package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.ProductDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysProduct;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysProductMapstruct extends BaseMapstruct<ProductDto, SysProduct> {
}
