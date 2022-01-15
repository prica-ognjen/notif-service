package com.badpc.notif.repository;

import com.badpc.notif.domain.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {

    @Query("select a from Archive a where a.email = ?1")
    List<Archive> findAllFor(String email);
}
