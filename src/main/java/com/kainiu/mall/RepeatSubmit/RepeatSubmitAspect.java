package com.kainiu.mall.RepeatSubmit;


import com.kainiu.mall.util.ResultData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
public class RepeatSubmitAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepeatSubmitAspect.class);

    @Autowired
    private RedisLock redisLock;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around("pointCut(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        int lockSeconds = noRepeatSubmit.lockTime();
        System.out.println(lockSeconds);

        HttpServletRequest request = RequestUtils.getRequest();
        Assert.notNull(request, "request can not null");

        // 此处可以用token或者JSessionId
        String token = request.getHeader("Authorization");
        String path = request.getServletPath();
        String key = getKey(token, path);
        String clientId = getClientId();

        System.out.println(path+"-"+key+"-"+clientId);

        boolean isSuccess = redisLock.tryLock(key, clientId, lockSeconds);
        LOGGER.info("tryLock key = [{}], clientId = [{}]", key, clientId);

        if (isSuccess) {
            LOGGER.info("tryLock success, key = [{}], clientId = [{}]", key, clientId);
            // 获取锁成功
            Object result;

            try {
                // 执行进程
                result = pjp.proceed();
            } finally {
                // 解锁
                redisLock.releaseLock(key, clientId);
                LOGGER.info("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
            }

            return result;

        } else {
            // 获取锁失败，认为是重复提交的请求
            LOGGER.info("tryLock fail, key = [{}]", key);
            return ResultData.error("重复提交");
        }

    }

    private String getKey(String token, String path) {
        return token + path;
    }

    private String getClientId() {
        return UUID.randomUUID().toString();
    }
}
