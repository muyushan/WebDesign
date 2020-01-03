package com.sane.dh.service.impl;

import com.sane.dh.dao.PersistentLoginsMapper;
import com.sane.dh.model.PersistentLogins;
import com.sane.dh.model.PersistentLoginsCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository {

    @Autowired
    private PersistentLoginsMapper persistentLoginsMapper;
    @Override
    public void createNewToken(PersistentRememberMeToken token) {

        PersistentLogins persistentLogins=new PersistentLogins();
        persistentLogins.setSeries(token.getSeries());
        persistentLogins.setUsername(token.getUsername());
        persistentLogins.setToken(token.getTokenValue());
        persistentLogins.setLastused(token.getDate()==null?new Date():token.getDate());
        persistentLoginsMapper.insertSelective(persistentLogins);

    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentLogins persistentLogins=new PersistentLogins();
        persistentLogins.setToken(tokenValue);
        persistentLogins.setLastused(lastUsed);
        persistentLogins.setSeries(series);
        persistentLoginsMapper.updateByPrimaryKeySelective(persistentLogins);

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogins pl=persistentLoginsMapper.selectByPrimaryKey(seriesId);
        PersistentRememberMeToken token=null;
        if(pl!=null){
            token=new PersistentRememberMeToken(pl.getUsername(),pl.getSeries(),pl.getToken(),pl.getLastused());

        }
        return token;
    }

    @Override
    public void removeUserTokens(String username) {
        PersistentLoginsCriteria persistentLoginsCriteria=new PersistentLoginsCriteria();
        PersistentLoginsCriteria.Criteria criteria=persistentLoginsCriteria.createCriteria();
        criteria.andUsernameEqualTo(username);
        persistentLoginsMapper.deleteByExample(persistentLoginsCriteria);

    }
}
