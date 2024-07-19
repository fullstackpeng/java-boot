package com.fullstackpeng.common.base.mapstruct;

import java.util.List;

public interface BaseMapstruct<D, E> {
    /**
     * dto转entity
     *
     * @param dto dto
     * @return entity
     */
    E toEntity(D dto);

    /**
     * entity转dto
     *
     * @param entity entity
     * @return dto
     */
    D toDto(E entity);

    /**
     * dtoList转entityList
     *
     * @param dtoList dtoList
     * @return entityList
     */
    List<E> toEntityList(List<D> dtoList);

    /**
     * entityList转dtoList
     *
     * @param entityList entityList
     * @return dtoList
     */
    List<D> toDtoList(List<E> entityList);
}
