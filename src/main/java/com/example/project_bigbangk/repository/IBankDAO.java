package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Bank;

import java.util.List;

/**
 * Dit is een interfase om informatie over een bank uit de database te kunnen halen.
 * Als de DOA hieraan voeldoet kun je makkelijk tussen verschillende databanken wisselen.
 *
 * @Author Kelly Speelman - de Jonge
 */

public interface IBankDAO {

    public void saveBank(Bank bank);

    public Bank findBank(String naam);

    public List<Bank> findAllBank();

    public void updateBank(Bank bank);
}
