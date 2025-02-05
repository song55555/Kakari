package com.gbi.kakari.dto;

public abstract class EntityDTO<TDTO, TEntity> {
	public abstract TEntity toEntity(TDTO dto);

	public abstract TDTO fromEntity(TEntity entity);
}
