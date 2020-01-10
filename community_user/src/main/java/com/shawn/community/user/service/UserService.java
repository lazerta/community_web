package com.shawn.community.user.service;

import com.shawn.community.user.Util.Const;
import com.shawn.community.user.entity.User;
import com.shawn.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private Random random = new Random();
    private final RedisTemplate redisTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final IdWorker idWorker;
    private final BCryptPasswordEncoder encoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public Page<User> findSearch(User user, int page, int size) {
        user.setId(null);
        Example<User> example = Example.of(user, criteriaMatcher());
        Pageable pageable = PageRequest.of(page - 1, size);
        return userRepository.findAll(example, pageable);
    }

    public List<User> findSearch(User user) {
        Example<User> example = Example.of(user, criteriaMatcher());
        return userRepository.findAll(example);
    }

    private ExampleMatcher criteriaMatcher() {
        return ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    public User findById(String id) {
        return userRepository.findById(id).get();
    }


    public void update(User user) {
        userRepository.save(user);
    }

    public void register(User user, Integer code) {
        user.setId(idWorker.nextId() + "");
        user.setFollowCount(0);
        user.setFanCount(0);
        user.setLoginDuration(0L);
        user.setRegisterDate(new Date());
        user.setUpdateDate(new Date());
        user.setLastLoginDate(new Date());
        userRepository.save(user);

    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }


    public void sendVerificationMessage(String mobile) {
        int checkCode = generateSixDigitRandomNumber();
        // cache checkcode
        redisTemplate.opsForValue().set(Const.smsCode + mobile, checkCode, 5, TimeUnit.MINUTES);
        // send checkcode to customer
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put(Const.smsCode, checkCode + "");
        rabbitTemplate.convertAndSend("sms", map);


    }

    private Integer generateSixDigitRandomNumber() {
        int max = 999_999;
        int min = 100_000;
        int code = random.nextInt(max);
        if (code < min) {
            code += min;

        }
        return code;

    }

    public User findByMobileAndPassword(User user) {
        User loginUser = userRepository.findByMobileAndPassword(user.getMobile(), user.getPassword());

        if (loginUser != null && encoder.matches(user.getPassword(), loginUser.getPassword())) {
            return loginUser;
        }
        return null;
    }
    @Transactional
    public void addFanAndFollowCount(String userId, String friendid, int amount) { userRepository.addFanCount(friendid, amount);
      userRepository.addFollowCount(userId, amount);
    }
}
