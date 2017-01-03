package es.gorka.edu.assembler;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class Assembler<IEntityDto, IEntity> {

	public IEntity toEntity(IEntityDto dto, IEntity entity) {
		Assert.notNull(dto);
		Assert.notNull(entity);
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	public IEntityDto toDto(IEntityDto dto, IEntity entity) {
		Assert.notNull(dto);
		Assert.notNull(entity);
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

}
