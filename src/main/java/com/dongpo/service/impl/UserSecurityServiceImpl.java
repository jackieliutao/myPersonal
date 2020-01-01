package com.dongpo.service.impl;
import com.dongpo.domain.Manager;
import com.dongpo.mapper.ManagerMapper;
import com.dongpo.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service("userSecurity")
public class UserSecurityServiceImpl implements UserSecurityService {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户
        Manager manager = managerMapper.getManageByName(username);
        if(manager == null){
            return null;
        }
        if(!manager.getStatus()){
            return null;
        }
        //设置权限
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //授权
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //返回用户名、密码、角色
        User user = new User(username, "{noop}"+manager.getPassword(), grantedAuthorities);
        return user;
    }
}
