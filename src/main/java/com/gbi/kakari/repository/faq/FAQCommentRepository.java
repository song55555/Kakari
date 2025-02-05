package com.gbi.kakari.repository.faq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.faq.FAQComment;

@Repository
public interface FAQCommentRepository extends JpaRepository<FAQComment, Integer> {
}
