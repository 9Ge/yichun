package com.enercomn.config;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号存储策略自动配置.
 *
 * @author someone
 */
@Configuration
@RequiredArgsConstructor
public class WxMpStorageAutoConfiguration {
  private final WxMpProperties properties;

//  @Autowired(required = false)
//  private JedisPool jedisPool;

//  @Autowired(required = false)
//  private RedissonClient redissonClient;

  @Bean
  @ConditionalOnMissingBean(WxMpConfigStorage.class)
  public WxMpConfigStorage wxMpInMemoryConfigStorage() {
    WxMpProperties.ConfigStorage storage = properties.getConfigStorage();
    WxMpProperties.StorageType type = storage.getType();

//    if (type == WxMpProperties.StorageType.redis) {
//      return getWxMpInRedisConfigStorage();
//    }
    return getWxMpInMemoryConfigStorage();
  }

  private WxMpDefaultConfigImpl getWxMpInMemoryConfigStorage() {
    WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
    setWxMpInfo(config);
    return config;
  }

//  private WxMpRedisConfigImpl getWxMpInRedisConfigStorage() {
//    JedisPool poolToUse = jedisPool;
//    if (poolToUse == null) {
//      poolToUse = getJedisPool();
//    }
//    WxMpRedisConfigImpl config = new WxMpRedisConfigImpl(poolToUse);
//    setWxMpInfo(config);
//    return config;
//  }

  private void setWxMpInfo(WxMpDefaultConfigImpl config) {
    config.setAppId(properties.getAppId());
    config.setSecret(properties.getSecret());
    config.setToken(properties.getToken());
    config.setAesKey(properties.getAesKey());
  }

//  private JedisPool getJedisPool() {
//    WxMpProperties.ConfigStorage storage = properties.getConfigStorage();
//    RedisProperties redis = storage.getRedis();
//
//    JedisPoolConfig config = new JedisPoolConfig();
//    if (redis.getMaxActive() != null) {
//      config.setMaxTotal(redis.getMaxActive());
//    }
//    if (redis.getMaxIdle() != null) {
//      config.setMaxIdle(redis.getMaxIdle());
//    }
//    if (redis.getMaxWaitMillis() != null) {
//      config.setMaxWaitMillis(redis.getMaxWaitMillis());
//    }
//    if (redis.getMinIdle() != null) {
//      config.setMinIdle(redis.getMinIdle());
//    }
//    config.setTestOnBorrow(true);
//    config.setTestWhileIdle(true);
//
//    JedisPool pool = new JedisPool(config, redis.getHost(), redis.getPort(),
//      redis.getTimeout(), redis.getPassword(), redis.getDatabase());
//    return pool;
//  }
}
