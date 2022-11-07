package com.backend.domain.tag.repository;

import com.backend.domain.tag.domain.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

	boolean existsByName(String name);

	Optional<Tag> findByName(String name);
}
