package com.mahajan.Expenseker.repository;

import org.springframework.data.repository.CrudRepository;

import com.mahajan.Expenseker.model.UserExpense;

public interface UserExpenseRepository extends CrudRepository<UserExpense, Integer> {

}
