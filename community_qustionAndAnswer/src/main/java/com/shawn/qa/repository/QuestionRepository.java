package com.shawn.qa.repository;

import com.shawn.qa.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, String> {
    /**
     * @return Page<Problem>
     * @apiNote Find the latest problem with given label
     */
    @Query(value = "SELECT * FROM tbl_question,tbl_question_label WHERE id = question_id AND label_id =? ORDER BY reply_time DESC ", nativeQuery = true)
    Page<Question> findLatestProblemsOfLabel(String labelId, Pageable pageable);

    @Query(value = "SELECT * FROM tbl_question,tbl_question_label WHERE id = question_id AND label_id =? ORDER BY reply_count DESC ", nativeQuery = true)
    Page<Question> findMostPopularQuestionsOfLabel(String labelId, Pageable pageable);

    @Query(value = "SELECT * FROM tbl_question,tbl_question_label WHERE id = question_id AND label_id =?  AND reply_count = 0 ORDER BY create_time desc", nativeQuery = true)
    Page<Question> findNotAnsweredQuestionOfLabel(String labelId, Pageable pageable);


}
