package io.vengine.hanoi.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.common.entity.DocumentNumber;

@Repository
public interface DocumentNumberRepository extends JpaRepository<DocumentNumber, Long> {

	public DocumentNumber findByType(String string);

}