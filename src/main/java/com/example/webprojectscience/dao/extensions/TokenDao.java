package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Token;

public interface TokenDao extends DAO<Token> {
    Token getByValue(String value);
    Token getByUserId(Long id);
    Token getByIpAddress(String address);

    boolean deleteByValue(String value);
    boolean deleteByUserID(Long id);
    boolean deleteByIpAddress(String address);
}
