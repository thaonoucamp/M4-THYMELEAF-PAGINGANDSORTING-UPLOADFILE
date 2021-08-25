package com.codegym.repository;

import com.codegym.model.ClassRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassRoomRepository extends CrudRepository<ClassRoom, Long> {
}
