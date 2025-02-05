package com.gbi.kakari.repository.faq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.faq.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Integer> {
}
