package net.win.dao;

import net.win.BaseDAO;
import net.win.entity.NewsEntity;
import net.win.entity.NewsTypeEntity;

import org.springframework.stereotype.Repository;

@Repository("newsDAO")
public final class NewsDAO extends BaseDAO<NewsEntity> {

}
