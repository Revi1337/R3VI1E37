package com.example.privmall.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity @Table(name = "ATTACHMENT") @Getter
public class Attachment {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "FILE_ID")
    public UUID id;

    @Column(name = "FILE_NAME", nullable = false, length = 30)
    private String filename;

    @Column(name = "MIME", nullable = false, length = 20)
    private String fileType;

    @Lob
    @Column(name = "DATA", nullable = false)
    private byte[] data;

    @Setter(value = AccessLevel.PACKAGE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    protected Attachment() {}

    @Builder(builderMethodName = "create")
    private Attachment(String filename, String fileType, byte[] data) {
        this.filename = filename;
        this.fileType = fileType;
        this.data = data;
    }

}
