package com.badpc.notif.controller;

import com.badpc.notif.domain.Archive;
import com.badpc.notif.dto.ArchiveFilterDto;
import com.badpc.notif.security.CheckSecurity;
import com.badpc.notif.security.service.TokenService;
import com.badpc.notif.service.ArchiveService;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/archive")
public class ArchiveController {

    private final ArchiveService archiveService;
    private final TokenService tokenService;

    public ArchiveController(ArchiveService archiveService, TokenService tokenService) {
        this.archiveService = archiveService;
        this.tokenService = tokenService;
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<Archive>> getArchives(@RequestHeader("Authorization") String authorization){
        return archiveService.getAll();
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<List<Archive>> getFilteredArchive(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ArchiveFilterDto archiveFilterDto){
        return archiveService.getFilteredArchive(archiveFilterDto);
    }

    @CheckSecurity(roles = {"ROLE_USER", "ROLE_MANAGER"})
    @GetMapping("/personalArchive")
    public ResponseEntity<List<Archive>> getPersonalArchives(
            @RequestHeader("Authorization") String authorization){

        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);

        Long l = new Long((Integer)claims.get("id"));

        return archiveService.getPersonalArchives(l, authorization);
    }

}
