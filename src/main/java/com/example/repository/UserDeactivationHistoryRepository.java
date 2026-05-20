package com.example.repository;

import com.example.model.UserDeactivationHistory;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface UserDeactivationHistoryRepository extends JpaRepository<UserDeactivationHistory, Long> {
    UserDeactivationHistory findTopByUserIdOrderByDeactivationDateDesc(Long userId);
    @Query("""
        select h
        from UserDeactivationHistory h
        where h.deactivationReason = com.example.enums.DeactivationReasons.TEMP_BLOCK
        and h.deactivationDate >= :from
        and h.deactivationDate < :to
        and not exists (
            select 1
            from UserDeactivationHistory h2
            where h2.userId = h.userId
            and (
                h2.deactivationDate > h.deactivationDate
                or(
                    h2.deactivationDate = h.deactivationDate
                                        and h2.id > h.id
                    )
                )
            )
    """)
    List<UserDeactivationHistory> findAllUsersToTerminateInRange(
            @Param("from")
            LocalDateTime from,
            @Param("to")
            LocalDateTime to);

}
