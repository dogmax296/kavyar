package ua.kavyar.service.mapper;

public interface RequestDtoMapper<M, Q> {
    M mapToModel(Q q);
}
