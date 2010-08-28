package net.win.dao;

import net.win.BaseDAO;
import net.win.entity.UserEntity;

import org.springframework.stereotype.Repository;

@Repository("userDAO")
public final class UserDAO extends BaseDAO<UserEntity> {

}
