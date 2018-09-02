package com.apartdeman.model.service;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 31.08.2018
 * @description TODO: Class Description
 */
public interface BaseService<T,K> {
    T save(K t, Long userId);
    T update(K t, Long userId);
    T deleteSoft(Long id,Long userId);
    T getById(Long id);
}
