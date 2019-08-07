package ua.palindromes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.palindromes.models.History;

public interface HistoryRepository extends JpaRepository<History, Integer> {

}
