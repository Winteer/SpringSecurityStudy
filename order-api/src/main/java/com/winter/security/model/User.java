//package com.winter.security.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
// //删除订单服务安全相关代码
///**
// * @ClassName : User
// * @Description : 用户model
// * @Author : Winter
// * @Date: 2020-10-29 14:48
// */
//public class User implements UserDetails {
//
////    private static final long serialVersionUID = 1L;
//
//    private Long id;
//
//    private String username;
//
//    private String password;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
