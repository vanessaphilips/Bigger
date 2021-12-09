package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Bank;

import java.util.List;

/**
 * created by Kelly Speelman - de Jonge
 */

public interface IBankDAO {

    public void saveBank(Bank bank);

    public Bank findBank(String naam);

    public List<Bank> findAllBank();

    public void updateBank(Bank bank);
}
