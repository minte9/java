package com.example.fx.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FxTransferRepository extends JpaRepository<FxTransfer, Long> {
    Optional<FxTransfer> findByIdempotencyKey(String idempotencyKey);
}
