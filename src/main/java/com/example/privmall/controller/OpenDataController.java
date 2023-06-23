package com.example.privmall.controller;

import com.example.privmall.dto.data.TotalUserDto;
import com.example.privmall.dto.data.UserRankDto;
import com.example.privmall.dto.data.UserSkillSet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController @RequiredArgsConstructor
public class OpenDataController {

    private final RestTemplate restTemplate;

    @GetMapping(path = "/api/data/tryhackme/users")
    public ResponseEntity<TotalUserDto> fetchTryHackMeTotalUsers() {
        URI uri = UriComponentsBuilder.fromUriString("https://tryhackme.com")
                .path("/api/site-stats")
                .build()
                .toUri();
        TotalUserDto totalUser = restTemplate.getForObject(uri, TotalUserDto.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(totalUser);
    }

    @GetMapping(path = "/api/data/tryhackme/user/{username}")
    public ResponseEntity<UserRankDto> fetchTryHackMeRank(@PathVariable(name = "username") String username) {
        URI uri = UriComponentsBuilder.fromUriString("https://tryhackme.com")
                .path("/api/user/rank/{username}")
                .buildAndExpand(username)
                .toUri();
        UserRankDto userRankDto = restTemplate.getForObject(uri, UserRankDto.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRankDto);
    }

    @GetMapping(path = "/api/data/tryhackme/user/skills")
    public ResponseEntity<UserSkillSet> fetchTryHackMeSkillSet() {
        URI uri = UriComponentsBuilder.fromUriString("https://tryhackme.com")
                .path("/api/user/skills/data")
                .build()
                .toUri();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Cookie", "_hjSessionUser_1950941=eyJpZCI6IjgyYzc4NGU5LTY5OTctNWQxNy05YzEyLTkzMTkxMDMyMWQwNSIsImNyZWF0ZWQiOjE2ODYzNjk4MzU2ODksImV4aXN0aW5nIjp0cnVlfQ==; connect.sid=s%3AmBPnluu-by-b_-HaDId3HbP6xMGaQjBo.x0gM703fgsPIScPnYAV8GKm4ZQN5aODrUH%2BwN%2BYRVO8; logged-in-hint=true; _csrf=HTZzeeD6RYEN9l0b4pdojSej; _hjIncludedInSessionSample_1950941=0; _hjSession_1950941=eyJpZCI6IjQzNjNmNmUyLTgxNjUtNGMwNS04NjgzLTYzNTg1YTIwMmI1ZiIsImNyZWF0ZWQiOjE2ODczMDI0OTE2MzcsImluU2FtcGxlIjpmYWxzZX0=; _hjAbsoluteSessionInProgress=0; _hjHasCachedUserAttributes=true; intercom-session-pgpbhph6=NzhJRlJRU1UyNUlOKzVaSU8yVTNuM1lzYWVndG5FTFNPN2txYmZKQ1JDR1RWOGw3WU9ZUVdDelNlQlhtWTRmOS0tMjFNbm5KZVFqK3dMMlVwZTAxYXlrdz09--a6ef5a98a377f7a8e8b3f5acc0761227f7ceb59b; _ga_Z8D4WL3D4P=GS1.1.1687302491.13.1.1687302518.0.0.0; AWSALB=NqxRF9aEIJ+8fEqyPcb24nX+N+09zszJPbdyPWrv6gllpJKI54hCdFnv75VFO6MrYcu5VVC51I+/0O5V7pNf+FYSwvMLpOGvO9ibaoxNgz7ClKLUBUdCFh7MdP0U");
        HttpEntity<UserSkillSet> httpEntity = new HttpEntity<>(headers);
        UserSkillSet userSkillSet = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, UserSkillSet.class).getBody();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userSkillSet);
    }
}
