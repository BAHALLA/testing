package com.octo.validators;

import com.octo.models.TransferRequestDTO;

public interface TransferValidator {
    void validateTransferRequest(TransferRequestDTO transferRequestDTO);
}
