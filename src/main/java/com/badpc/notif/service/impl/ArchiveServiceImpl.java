package com.badpc.notif.service.impl;


import com.badpc.notif.domain.Archive;
import com.badpc.notif.dto.ArchiveFilterDto;
import com.badpc.notif.dto.UserMailDto;
import com.badpc.notif.repository.ArchiveRepository;
import com.badpc.notif.service.ArchiveService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ArchiveServiceImpl  implements ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final RestTemplate restTemplate;

    public ArchiveServiceImpl(ArchiveRepository archiveRepository, RestTemplate restTemplate) {
        this.archiveRepository = archiveRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<List<Archive>> getAll() {
        List<Archive> arhive = archiveRepository.findAll();
        return new ResponseEntity<>(arhive, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Archive>> getFilteredArchive(ArchiveFilterDto archiveFilterDto) {

        List<Archive> list = archiveRepository.findAll();

        if(archiveFilterDto.getMail() != null && !archiveFilterDto.getMail().equals("")){
            list.removeIf(el -> (!el.getEmail().equals(archiveFilterDto.getMail())));
        }

        if(archiveFilterDto.getNotificationType() != null && !archiveFilterDto.getNotificationType().equals("")){
            list.removeIf(el -> (!el.getTipNotifikacije().equals(archiveFilterDto.getNotificationType())));
        }

        if(archiveFilterDto.getStart() != null && archiveFilterDto.getEnd() != null){
            for(int i = 0; i < list.size(); i++){
                System.out.println("LIST DATE: " + list.get(i).getDate() + "DTO DATE: " + archiveFilterDto.getStart());

            }
            list.removeIf(el -> (el.getDate().compareTo(archiveFilterDto.getStart())) < 0 || el.getDate().compareTo(archiveFilterDto.getEnd()) > 0);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Archive>> getPersonalArchives(Long l, String jwt) {

        System.out.println(jwt);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwt);

        HttpEntity<String> request = new HttpEntity<String>(headers);

        UserMailDto userMailDto = restTemplate.exchange("/service/forArchive/"+l, HttpMethod.GET, request, UserMailDto.class).getBody();

        assert userMailDto != null;
        String email = ((UserMailDto)userMailDto).getEmail();

        return new ResponseEntity<>(archiveRepository.findAllFor(email), HttpStatus.OK);

    }
}
