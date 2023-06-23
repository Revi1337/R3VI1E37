package com.example.privmall.dto.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UserSkillSet {

    private SkillsDTO skills;
    private List<String> recommendations;
}

@Data
class SkillsDTO {
    private LinuxDTO linux;
    private WebDTO web;
    private WindowsDTO windows;
    private FundamentalsDTO fundamentals;
    private PrivescDTO privesc;
    private NetworkDTO network;
}

@Data
class LinuxDTO {
    private int score;
}

@Data
class WebDTO {
    private int score;
}

@Data
class WindowsDTO {
    private int score;
}

@Data
class FundamentalsDTO {
    private int score;
}

@Data
class PrivescDTO {
    private int score;
}

@Data
class NetworkDTO {
    private int score;
}
