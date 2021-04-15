package ru.grigan.test_task_universe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.grigan.test_task_universe.model.Lord;

import java.util.List;

public interface LordRepository extends JpaRepository<Lord, Integer> {

    List<Lord> findLordByPlanetsIsNull();

    @Query("select l from Lord l order by l.age")
    List<Lord> youngLord();
}
