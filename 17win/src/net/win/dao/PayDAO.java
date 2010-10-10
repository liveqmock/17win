package net.win.dao;

import net.win.BaseDAO;
import net.win.entity.PayEntity;
import net.win.entity.UserEntity;

import org.springframework.stereotype.Repository;

@Repository("payDAO")
public final class PayDAO extends BaseDAO<PayEntity> {
}
