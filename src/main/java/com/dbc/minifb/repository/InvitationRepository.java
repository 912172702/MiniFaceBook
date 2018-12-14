package com.dbc.minifb.repository;

import com.dbc.minifb.entity.Invitation;
import com.dbc.minifb.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/3 6:22 PM
 * @Description: You Know
 * @Version 1.0
 */
public interface InvitationRepository extends Neo4jRepository<Invitation, Long> {
    @Query("match(n:Invitation)-[r:POST_BY]->(b:User) where id(b)={0} return n order by n.time desc")
    List<Invitation> findInvitationsByPostByOrderByTimeDesc(Long id);

}
