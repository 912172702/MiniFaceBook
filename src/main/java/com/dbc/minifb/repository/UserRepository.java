package com.dbc.minifb.repository;


import com.dbc.minifb.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/2 12:09 AM
 * @Description: You Know
 * @Version 1.0
 */
@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
    List<User> findByUsernameEquals(String username);

    @Query(value = "MATCH (n:User) where n.username=~{0} return n")
    List<User> findByUsernameLike(String like);
}
