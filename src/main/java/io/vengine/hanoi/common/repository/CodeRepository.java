package io.vengine.hanoi.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.vengine.hanoi.common.entity.Code;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

	public Code findByTypeAndCode(String type, String code);

	public List<Code> findByTypeOrderBySeq(String type);

}
