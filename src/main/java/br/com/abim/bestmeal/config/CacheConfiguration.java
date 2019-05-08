package br.com.abim.bestmeal.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(br.com.abim.bestmeal.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.CartaoCredito.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.CartaoRecompensa.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.Menu.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.Produto.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.Logradouro.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.Municipio.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.Nome.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.Endereco.class.getName(), jcacheConfiguration);
            cm.createCache(br.com.abim.bestmeal.domain.Pessoa.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
