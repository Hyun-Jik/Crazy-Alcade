package com.eni.backend.problem.service;

import com.eni.backend.common.exception.CustomBadRequestException;
import com.eni.backend.member.entity.Language;
import com.eni.backend.problem.dto.request.PostCodeRequest;
import com.eni.backend.problem.dto.response.PostCodeResponse;
import com.eni.backend.problem.entity.Problem;
import com.eni.backend.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.eni.backend.common.response.BaseResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeService {

    private final ProblemRepository problemRepository;

    public Problem findProblemById(Long problemId) {
        return problemRepository.findById(problemId)
                .orElseThrow(() -> new CustomBadRequestException(PROBLEM_NOT_FOUND));
    }

    public Language getLanguage(String lang) {
        Language language;
        try {
            language = Language.valueOf(lang);
        } catch (Exception e) {
            throw new CustomBadRequestException(LANGUAGE_NOT_SUPPORTED);
        }
        return language;
    }

}
