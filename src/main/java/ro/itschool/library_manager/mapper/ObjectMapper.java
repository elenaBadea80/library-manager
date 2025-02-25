package ro.itschool.library_manager.mapper;


/**
 *
 * @param <D> is the DTO class
 * @param <E> is the Entity class
 */
public interface ObjectMapper<D, E> {

    D mapToDto(E entity);

    E mapToEntity(D dto);

}
