package com.badpc.notif.service;

import com.badpc.notif.domain.Archive;
import com.badpc.notif.dto.ArchiveFilterDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArchiveService {
    ResponseEntity<List<Archive>> getAll();

    ResponseEntity<List<Archive>> getFilteredArchive(ArchiveFilterDto archiveFilterDto);

    ResponseEntity<List<Archive>> getPersonalArchives(Long l, String authorization);
}
