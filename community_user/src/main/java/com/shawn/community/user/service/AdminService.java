package com.shawn.community.user.service;

import com.shawn.community.user.Util.Const;
import com.shawn.community.user.entity.Admin;
import exception.UnauthorizedException;
import com.shawn.community.user.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder;
    private final IdWorker idWorker;


    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(String id) {
        return adminRepository.findById(id).get();
    }

    public void save(Admin admin) {
        admin.setId(idWorker.nextId() + "");
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }

    public void update(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteById(String id, HttpServletRequest request) {
        String token = (String) request.getAttribute(Const.ADMIN_TOKEN);
        if (StringUtil.isNullOrBlank(token)) {
           throw  UnauthorizedException.create();
        }
        adminRepository.deleteById(id);
    }

    public Page<Admin> findSearch(Admin admin, int page, int size) {
        admin.setId(null);
        Example<Admin> example = Example.of(admin, criteriaMatcher());
        Pageable pageable = PageRequest.of(page - 1, size);
        return adminRepository.findAll(example, pageable);
    }

    public List<Admin> findSearch(Admin admin) {
        admin.setId(null);
        Example<Admin> example = Example.of(admin, criteriaMatcher());
        return adminRepository.findAll(example);
    }

    private ExampleMatcher criteriaMatcher() {
        return ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    public Admin login(Admin admin) {
        Admin byUsername = adminRepository.findByUsername(admin.getUsername());
        if (byUsername == null || !encoder.matches(admin.getPassword(), byUsername.getPassword())) {
            return null;
        }
        return byUsername;
    }
}
