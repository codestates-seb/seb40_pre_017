package com.backend.domain.tag.domain;

import com.backend.domain.question.domain.Question;
import com.backend.domain.question.domain.QuestionTag;
import com.backend.global.Audit.Auditable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tag extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    private Tag(String name) {
        this.name = name;
    }

    static public Tag of(String name){
        return new Tag(name);
    }

}
