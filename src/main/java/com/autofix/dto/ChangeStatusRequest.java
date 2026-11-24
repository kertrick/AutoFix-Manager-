package com.autofix.dto;

import com.autofix.model.RepairStatus;
import jakarta.validation.constraints.NotNull;

public class ChangeStatusRequest {

    @NotNull
    private RepairStatus status;

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }
}
