package com.octo.services;

import com.octo.models.TransferRequestDTO;


public interface TransferService {

    void performTransfer(TransferRequestDTO transferRequestDTO);
}
