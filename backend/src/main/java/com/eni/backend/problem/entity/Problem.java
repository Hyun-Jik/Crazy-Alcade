package com.eni.backend.problem.entity;

import com.eni.backend.code.entity.Code;
import com.eni.backend.common.entity.BaseTimeEntity;
import com.eni.backend.common.entity.ProblemPlatform;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProblemPlatform platform;

    @Column(nullable = false)
    private Integer no;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String description;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String input;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String output;

    @Column(nullable = false)
    private Integer time;

    @Column(nullable = false)
    private Integer memory;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.REMOVE)
    private List<Code> codes = new ArrayList<>();

    @OneToMany(mappedBy = "problem", cascade = CascadeType.REMOVE)
    private List<Testcase> testcases = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tier_id", referencedColumnName = "tier_id")
    private Tier tier;

    @Builder
    public Problem(ProblemPlatform platform, Integer no, String title, String input, String output, String description, Integer time, Integer memory, Tier tier) {
        this.platform = platform;
        this.no = no;
        this.title = title;
        this.description = description;
        this.time = time;
        this.memory = memory;
        this.tier = tier;
    }
}